# Traitement des données de l'emploi : Données INSEE, Source https://www.insee.fr/fr/statistiques/series/102759768, mis à jour le 31/03/2023
# Données des élections 2022 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-des-10-et-24-avril-2022-resultats-definitifs-du-1er-tour/ mis à jour le 14/04/2023
# Données des élections 2017 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-des-23-avril-et-7-mai-2017-resultats-du-1er-tour/ mis à jour le 24/04/2017
# Données des élections 2012 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-2012-resultats-572124/ mis à jour le 06/01/2014
# Données des élections 2007 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-2007-resultats-572120/ mis à jour le 06/01/2014
# Données sécurité par département : https://www.data.gouv.fr/fr/datasets/bases-statistiques-communale-et-departementale-de-la-delinquance-enregistree-par-la-police-et-la-gendarmerie-nationales/ mis à jour le 09/03/2023

import pandas as pd
import numpy as np


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

# Lire les fichier des données des élections 2022 dans les Alpes_Maritimes et la Loire-Atlantique
#Lire les données des autres élections :
#resultats_elections_06_44_2017 = pd.read_excel('Presidentielle_2017_Resultats_Tour_1_c.xls', sheet_name="Départements Tour 1")
#resultats_elections_06_44_2012 = pd.read_excel('Presidentielle_2012_Tour_1.xls', sheet_name="Départements T1")
#resultats_elections_06_44_2007 = pd.read_excel('Presidentielle_2007_Tour_1.xls', sheet_name="Départements T1")
resultats_elections_06_44_2022 = pd.read_excel('./data/Donnees_choisies_2022.xlsx')
# Affiche le dataframe
print(resultats_elections_06_44_2022)

# Lire le fichier sécurité
donnes_securite_dept = pd.read_csv("./data/donnee-securité.csv",
                                   sep=';')
# Filtrer les données de sécurité sur l'année 2022 et sur les départements 44 et 06
donnees_securite_2022_44_06 = donnes_securite_dept[(donnes_securite_dept['annee'] == 22) & (
            (donnes_securite_dept['Code.département'] == '6') | (donnes_securite_dept['Code.département'] == '06') | (
                donnes_securite_dept['Code.département'] == '44'))]

nb_faits_par_departements = donnees_securite_2022_44_06.groupby('Code.département').sum()['faits']

resultats_elections_06_44_2022['faits_de_violence'] = nb_faits_par_departements.values
print(resultats_elections_06_44_2022)

# Traite les données sur l'emploi
indicateurs_serie_emploi = pd.read_csv("./data/caractéristiques.csv", sep=";")
print(indicateurs_serie_emploi)
indicateurs_serie_emploi["N° Département"] = indicateurs_serie_emploi["Zone géographique"].apply(
    separer_numero_departement)
print(indicateurs_serie_emploi)
indicateurs_serie_emploi_44_06 = indicateurs_serie_emploi[
    (indicateurs_serie_emploi['N° Département'] == '44') | (indicateurs_serie_emploi['N° Département'] == '06')]

valeurs_trimestrielles_series_emploi = pd.read_csv("./data/valeurs_trimestrielles.csv", sep=";",
                                                   dtype={'idBank': object})
if valeurs_trimestrielles_series_emploi is not None:
    # Sauvegarde des codes statistiques de chaque série
    codes_stats = valeurs_trimestrielles_series_emploi[valeurs_trimestrielles_series_emploi["Libellé"] == "Codes"]
    print(codes_stats)
    # Suppression des lignes avec les codes statistiques
    valeurs_trimestrielles_series_emploi = valeurs_trimestrielles_series_emploi.drop(codes_stats.index)
    print(valeurs_trimestrielles_series_emploi)
    indicateurs_serie_emploi_44_06["idBank"] = indicateurs_serie_emploi_44_06["idBank"].apply(lambda id: id.lstrip('0'))
    donnees_emploi_44_06 = pd.merge(indicateurs_serie_emploi_44_06, valeurs_trimestrielles_series_emploi, how='inner',
                                    on="idBank")
    print("Données emploi mergées : \n", donnees_emploi_44_06)
    
    noms_colonnes_interessantes = ["2007-T2","2012-T2","2017-T2","2022-T2"]
    for nom_colonne in noms_colonnes_interessantes:
        print(nom_colonne, " ", str((donnees_emploi_44_06[nom_colonne].isna().sum() / len(donnees_emploi_44_06) )* 100), "% de données manquantes")
    lignes_donnees_manquantes = donnees_emploi_44_06[
    (donnees_emploi_44_06["2007-T2"].isnull())|
    (donnees_emploi_44_06["2012-T2"].isnull())|
    (donnees_emploi_44_06["2017-T2"].isnull())|
    (donnees_emploi_44_06["2022-T2"].isnull())]
    print(lignes_donnees_manquantes[["idBank", "Dernière mise à jour_x"] + noms_colonnes_interessantes])