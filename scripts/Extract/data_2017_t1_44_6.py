import pandas as pd
import sqlite3

def extract_2017_t1():
    # Lire le fichier Excel
    df = pd.read_excel('./data/Presidentielle_2017_Resultats_Tour_1_c.xls', sheet_name='Circo. Leg. Tour 1')

    # Convertir la colonne 'Code du département' en chaîne de caractères
    df['Code du département'] = df['Code du département'].astype(str)
    print(df['Code du département'].unique())

    # Filtrer les données pour garder uniquement celles relatives à la Loire-Atlantique et aux Alpes-Maritimes
    dept_codes = ['44', '6']
    df_filtered = df[df['Code du département'].isin(dept_codes)]
    print(df_filtered.shape)
    print(df_filtered.head())

    print(df_filtered.columns.tolist())

    return df_filtered


def save_sql(df):
    # Établir une connexion à la base de données SQLite
    conn = sqlite3.connect('./data_output/bdd_election_2017_t1_44_6.db')
    cursor = conn.cursor()

    # Créer la table pour stocker les données
    cursor.execute('''
         CREATE TABLE IF NOT EXISTS resultats (
             code_departement INTEGER,
             libelle_departement TEXT,
             code_circonscription INTEGER,
             libelle_circonscription TEXT,
             inscrits INTEGER,
             abstentions INTEGER,
             pourcentage_abstentions REAL,
             votants INTEGER,
             pourcentage_votants REAL,
             blancs INTEGER,
             pourcentage_blancs_inscrits REAL,
             pourcentage_blancs_votants REAL,
             nuls INTEGER,
             pourcentage_nuls_inscrits REAL,
             pourcentage_nuls_votants REAL,
             exprimes INTEGER,
             pourcentage_exprimes_inscrits REAL,
             pourcentage_exprimes_votants REAL,
             numero_panneau INTEGER,
             sexe TEXT,
             nom TEXT,
             prenom TEXT,
             voix INTEGER,
             pourcentage_voix_inscrits REAL,
             pourcentage_voix_exprimes REAL,
             numero_panneau_2 INTEGER,
             sexe_2 TEXT,
             nom_2 TEXT,
             prenom_2 TEXT,
             voix_2 INTEGER,
             pourcentage_voix_inscrits_2 REAL,
             pourcentage_voix_exprimes_2 REAL,
             numero_panneau_3 INTEGER,
             sexe_3 TEXT,
             nom_3 TEXT,
             prenom_3 TEXT,
             voix_3 INTEGER,
             pourcentage_voix_inscrits_3 REAL,
             pourcentage_voix_exprimes_3 REAL,
             numero_panneau_4 INTEGER,
             sexe_4 TEXT,
             nom_4 TEXT,
             prenom_4 TEXT,
             voix_4 INTEGER,
             pourcentage_voix_inscrits_4 REAL,
             pourcentage_voix_exprimes_4 REAL,
             numero_panneau_5 INTEGER,
             sexe_5 TEXT,
             nom_5 TEXT,
             prenom_5 TEXT,
             voix_5 INTEGER,
             pourcentage_voix_inscrits_5 REAL,
             pourcentage_voix_exprimes_5 REAL,
             numero_panneau_6 INTEGER,
             sexe_6 TEXT,
             nom_6 TEXT,
             prenom_6 TEXT,
             voix_6 INTEGER,
             pourcentage_voix_inscrits_6 REAL,
             pourcentage_voix_exprimes_6 REAL,
             numero_panneau_7 INTEGER,
             sexe_7 TEXT,
             nom_7 TEXT,
             prenom_7 TEXT,
             voix_7 INTEGER,
             pourcentage_voix_inscrits_7 REAL,
             pourcentage_voix_exprimes_7 REAL,
             numero_panneau_8 INTEGER,
             sexe_8 TEXT,
             nom_8 TEXT,
             prenom_8 TEXT,
             voix_8 INTEGER,
             pourcentage_voix_inscrits_8 REAL,
             pourcentage_voix_exprimes_8 REAL,
             numero_panneau_9 INTEGER,
             sexe_9 TEXT,
             nom_9 TEXT,
             prenom_9 TEXT,
             voix_9 INTEGER,
             pourcentage_voix_inscrits_9 REAL,
             pourcentage_voix_exprimes_9 REAL,
             numero_panneau_10 INTEGER,
             sexe_10 TEXT,
             nom_10 TEXT,
             prenom_10 TEXT,
             voix_10 INTEGER,
             pourcentage_voix_inscrits_10 REAL,
             pourcentage_voix_exprimes_10 REAL,
             numero_panneau_11 INTEGER,
             sexe_11 TEXT,
             nom_11 TEXT,
             prenom_11 TEXT,
             voix_11 INTEGER,
             pourcentage_voix_inscrits_11 REAL,
             pourcentage_voix_exprimes_11 REAL
         )
     ''')

    # Insérer les données dans la base de données
    for _, row in df.iterrows():
        values = tuple(row)
        cursor.execute('''
             INSERT INTO resultats VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
              ?, ?, ?, ?, ?, ?, ?, ?, ?, 
             ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
             ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         ''', values)

    # Valider les modifications et fermer la connexion à la base de données
    conn.commit()
    conn.close()
