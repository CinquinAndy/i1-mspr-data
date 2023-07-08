# Traitement des données de l'emploi : Données INSEE, Source https://www.insee.fr/fr/statistiques/series/102759768, mis à jour le 31/03/2023
# Données des élections 2022 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-des-10-et-24-avril-2022-resultats-definitifs-du-1er-tour/ mis à jour le 14/04/2023
# Données des élections 2017 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-des-23-avril-et-7-mai-2017-resultats-du-1er-tour/ mis à jour le 24/04/2017
# Données des élections 2012 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-2012-resultats-572124/ mis à jour le 06/01/2014
# Données des élections 2007 : https://www.data.gouv.fr/fr/datasets/election-presidentielle-2007-resultats-572120/ mis à jour le 06/01/2014
# Données sécurité par département : https://www.data.gouv.fr/fr/datasets/bases-statistiques-communale-et-departementale-de-la-delinquance-enregistree-par-la-police-et-la-gendarmerie-nationales/ mis à jour le 09/03/2023

import pandas as pd
import numpy as np
import math
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score
import logging
import re
import sqlite3
#Définition de constantes
AXE_COLONNES = 1

def separer_numero_departement(valeur):
    valeur_a_comparer = valeur.strip().lower()
    if valeur_a_comparer == "loire atlantique":
       return 44
    elif valeur_a_comparer == "alpes-maritimes":
       return 6
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
sheet_names = ['Emploi LA', 'Emploi Indus LA', 'Tertiaire marchand LA', 'Tertiaire non march LA', 'Chom LA', 'Emploi AM', 'Emploi Indus AM', 'Tertiaire march AM', 'Tertiaire non march AM OK', 'Chom AM']
dict_donnees_insee = pd.read_excel("./data/donnees_insee.xlsx", sheet_name=sheet_names, skiprows=6)
dict_donnees_insee['Tertiaire non march AM OK'] = dict_donnees_insee['Tertiaire non march AM OK'].drop(33, axis=0)
print(dict_donnees_insee.keys())

for item in dict_donnees_insee.items():
   dataframe = dict_donnees_insee[item[0]]
   dataframe = dataframe.dropna(axis=0, how="all")
   print(dataframe)
   dict_donnees_insee[item[0]] = dataframe
   #print(item[0], str(dict_donnees_insee[item[0]].shape))
dataframes = dict_donnees_insee.values()
dataset_insee = pd.concat(dataframes, axis=0,keys=sheet_names).reset_index(level=1,drop=True)
dataset_insee.insert(0,"N°_Departement",dataset_insee["Zone_geographique"].apply(separer_numero_departement))
dataset_insee = dataset_insee.drop("Zone_geographique", axis=AXE_COLONNES)
print(dataset_insee)

#Affichage des noms de colonnes ayant des valeurs manquantes

print(afficher_noms_colonnes_avec_valeurs_manquantes(dataset_insee))
nan_values_dataframe = dataset_insee.isna()
missing_values_row_indexes = nan_values_dataframe[(nan_values_dataframe["2022T1"] == True)|(nan_values_dataframe["2022T2"] == True)|(nan_values_dataframe["2022T3"].isnull().any() == True)|(nan_values_dataframe["2022T4"] == True)].index
missing_values_row_indexes
rows_w_missing_values = dataset_insee.loc[missing_values_row_indexes]
rows_w_missing_values.columns = range(rows_w_missing_values.columns.size)
predicted_y_values_dict = {}
# Régression linéaire sur les données manquantes
previous_data_slice = slice(1,82)
target_slice = slice(82, None)
#Plaçage des features et des labels dans des tableaux à une dimension, Travail avec les données emploi Indus LA
x = rows_w_missing_values.columns[previous_data_slice].to_numpy().reshape(-1,1)
for i in range(len(rows_w_missing_values)):
    current_series_name = rows_w_missing_values.index[i]
    y = rows_w_missing_values.iloc[i,previous_data_slice].to_numpy().reshape(-1,1)
    #Découpage des données en jeux d'entraînement et de test
    #splitted_datasets_tuple = train_test_split(x, y, test_size=0.2, train_size=0.8)
    #Préparation des données à la régression linéaire
    #X_train, X_test, y_train, y_test = map(lambda dataset : dataset.reshape(1,dataset.shape[0]), splitted_datasets_tuple)
    
    regression_model = LinearRegression()
    # Adapter les données (entraînement du modèle)
    regression_model.fit(x,y)
    # Prédiction
    y_predicted = regression_model.predict(x)
    predicted_y_values_dict[current_series_name] = y_predicted
    r2 = r2_score(y, y_predicted)
    corr_coef = math.sqrt(r2)
    print(corr_coef)
#Insertion des données dans une base de données
connexion_donnees_emploi = sqlite3.connect("Emploi4406.db")