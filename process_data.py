# Traitement des données de l'emploi : Données INSEE, Source https://www.insee.fr/fr/statistiques/series/102759768, mis à jour le 31/03/2023
# Données des élections 2022 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-des-10-et-24-avril-2022-resultats-definitifs-du-1er-tour/ mis à jour le 14/04/2023
# Données des élections 2017 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-des-23-avril-et-7-mai-2017-resultats-du-1er-tour/ mis à jour le 24/04/2017
# Données des élections 2012 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-2012-resultats-572124/ mis à jour le 06/01/2014
# Données des élections 2007 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-2007-resultats-572120/ mis à jour le 06/01/2014
# Données sécurité par département : https://www.data.gouv.fr/fr/datasets/bases-statistiques-communale-et-departementale-de-la-delinquance-enregistree-par-la-police-et-la-gendarmerie-nationales/ mis à jour le 09/03/2023

import pandas as pd
import numpy as np
import sklearn as sk
import logging
import re
#Définition de constantes
AXE_COLONNES = 1

def separer_numero_departement(valeur):
    tableau_composants_valeur = str(valeur).split("-", maxsplit=1)
    if len(tableau_composants_valeur) < 2:
        return None
    else:
        numero_departement = tableau_composants_valeur[0].strip()
        return numero_departement
def afficher_noms_colonnes_avec_valeurs_manquantes(dataframe):
  column_labels = list(dataframe.columns)
  for column_label in column_labels:
    if dataframe[column_label].hasnans:
      print(column_label)
def extraire_secteur_activite(label):
    try:
        secteur_activite = re.sub("\(.*?\)", "",label).split("-")[1].strip()
        return secteur_activite
    except Exception as err:
        logging.warning("Problème avec le libellé " + label + "\n" + err +" \n La valeur a été laissée telle quelle. \n Un traitement manuel de la valeur sera nécessaire.")
        return label

# Lire les fichier des données des élections 2022 dans les Alpes_Maritimes et la Loire-Atlantique
#Lire les données des autres élections :
#resultats_elections_06_44_2017 = pd.read_excel('Presidentielle_2017_Resultats_Tour_1_c.xls', sheet_name="Départements Tour 1")
#resultats_elections_06_44_2012 = pd.read_excel('Presidentielle_2012_Tour_1.xls', sheet_name="Départements T1")
#resultats_elections_06_44_2007 = pd.read_excel('Presidentielle_2007_Tour_1.xls', sheet_name="Départements T1")
resultats_elections_06_44_2022 = pd.read_excel('./data/Donnees_choisies_2022.xlsx')
# Affiche le dataframe
#print(resultats_elections_06_44_2022)

# Lire le fichier sécurité
donnes_securite_dept = pd.read_csv("./data/donnee-securité.csv",
                                   sep=';')
# Filtrer les données de sécurité sur l'année 2022 et sur les départements 44 et 06
donnees_securite_2022_44_06 = donnes_securite_dept[(donnes_securite_dept['annee'] == 22) & (
            (donnes_securite_dept['Code.département'] == '6') | (donnes_securite_dept['Code.département'] == '06') | (
                donnes_securite_dept['Code.département'] == '44'))]

nb_faits_par_departements = donnees_securite_2022_44_06.groupby('Code.département').sum()['faits']

resultats_elections_06_44_2022['faits_de_violence'] = nb_faits_par_departements.values
#print(resultats_elections_06_44_2022)

# Traite les données sur l'emploi
#Lit le jeu de données de l'insee au niveau de l'emloi et du chômage
sheet_names = ['Emploi PL', 'Emploi Indus PL', 'Tertiaire marchand PL', 'Tertiaire non march PL', 'Chom PL', 'Emploi PACA', 'Emploi Indus PACA', 'Tertiaire march PACA', 'Tertiaire non march PACA', 'Chom PACA','Chom_France_hors_Mayotte']
dict_donnees_insee = pd.read_excel("./data/donnees_insee.xlsx", sheet_name=sheet_names, skiprows=6)

for item in dict_donnees_insee.items():
   dataframe = dict_donnees_insee[item[0]]
   dataframe = dataframe.dropna(axis=0, how="all")
   dict_donnees_insee[item[0]] = dataframe
   print(item[0], str(dict_donnees_insee[item[0]].shape))
dict_donnees_insee["Tertiaire non march PACA"] = dict_donnees_insee["Tertiaire non march PACA"].drop(34, axis=0)
dataframes = dict_donnees_insee.values()
dataset_insee = pd.concat(dataframes, axis=0,keys=sheet_names).reset_index(level=1,drop=True)
print(dataset_insee)