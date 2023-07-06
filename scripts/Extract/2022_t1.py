import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import sqlite3

results_df = pd.read_csv('../../data/2022_resultats-par-niveau-burvot-t1-france-entiere.csv', sep=';', encoding='cp1252')

# Convertir la colonne 'Code du département' en chaîne de caractères
results_df['Code du département'] = results_df['Code du département'].astype(str)
print(results_df['Code du département'].unique())

# Filtrer les données pour garder uniquement celles relatives à la Loire-Atlantique et aux Alpes-Maritimes
dept_codes = ['44', '6']
df_filtered = results_df[results_df['Code du département'].isin(dept_codes)]
print(df_filtered.shape)
print(df_filtered.head())

print(df_filtered.columns.tolist())

# Établir une connexion à la base de données SQLite
conn = sqlite3.connect('../../data_output/bdd_election_2022_t1.db')
cursor = conn.cursor()

# Créer la table pour stocker les données
cursor.execute('''
    CREATE TABLE IF NOT EXISTS election_results (
    Code_du_departement INTEGER,
    Libelle_du_departement TEXT,
    Code_de_la_circonscription INTEGER,
    Libelle_de_la_circonscription TEXT,
    Code_de_la_commune INTEGER,
    Libelle_de_la_commune TEXT,
    Code_du_b_vote INTEGER,
    Inscrits INTEGER,
    Abstentions INTEGER,
    Abs_Ins DECIMAL,
    Votants INTEGER,
    Vot_Ins DECIMAL,
    Blancs INTEGER,
    Blancs_Ins DECIMAL,
    Blancs_Vot DECIMAL,
    Nuls INTEGER,
    Nuls_Ins DECIMAL,
    Nuls_Vot DECIMAL,
    Exprimés INTEGER,
    Exp_Ins DECIMAL,
    Exp_Vot DECIMAL,
    N_Panneau INTEGER,
    Sexe TEXT,
    Nom TEXT,
    Prénom TEXT,
    Voix INTEGER,
    Voix_Ins DECIMAL,
    Voix_Exp DECIMAL,
    N_Panneau_1 INTEGER,
    Sexe_1 TEXT,
    Nom_1 TEXT,
    Prénom_1 TEXT,
    Voix_1 INTEGER,
    Voix_Ins_1 DECIMAL,
    Voix_Exp_1 DECIMAL,
    N_Panneau_2 INTEGER,
    Sexe_2 TEXT,
    Nom_2 TEXT,
    Prénom_2 TEXT,
    Voix_2 INTEGER,
    Voix_Ins_2 DECIMAL,
    Voix_Exp_2 DECIMAL,
    N_Panneau_3 INTEGER,
    Sexe_3 TEXT,
    Nom_3 TEXT,
    Prénom_3 TEXT,
    Voix_3 INTEGER,
    Voix_Ins_3 DECIMAL,
    Voix_Exp_3 DECIMAL,
    N_Panneau_4 INTEGER,
    Sexe_4 TEXT,
    Nom_4 TEXT,
    Prénom_4 TEXT,
    Voix_4 INTEGER,
    Voix_Ins_4 DECIMAL,
    Voix_Exp_4 DECIMAL,
    N_Panneau_5 INTEGER,
    Sexe_5 TEXT,
    Nom_5 TEXT,
    Prénom_5 TEXT,
    Voix_5 INTEGER,
    Voix_Ins_5 DECIMAL,
    Voix_Exp_5 DECIMAL,
    N_Panneau_6 INTEGER,
    Sexe_6 TEXT,
    Nom_6 TEXT,
    Prénom_6 TEXT,
    Voix_6 INTEGER,
    Voix_Ins_6 DECIMAL,
    Voix_Exp_6 DECIMAL,
    N_Panneau_7 INTEGER,
    Sexe_7 TEXT,
    Nom_7 TEXT,
    Prénom_7 TEXT,
    Voix_7 INTEGER,
    Voix_Ins_7 DECIMAL,
    Voix_Exp_7 DECIMAL,
    N_Panneau_8 INTEGER,
    Sexe_8 TEXT,
    Nom_8 TEXT,
    Prénom_8 TEXT,
    Voix_8 INTEGER,
    Voix_Ins_8 DECIMAL,
    Voix_Exp_8 DECIMAL,
    N_Panneau_9 INTEGER,
    Sexe_9 TEXT,
    Nom_9 TEXT,
    Prénom_9 TEXT,
    Voix_9 INTEGER,
    Voix_Ins_9 DECIMAL,
    Voix_Exp_9 DECIMAL,
    N_Panneau_10 INTEGER,
    Sexe_10 TEXT,
    Nom_10 TEXT,
    Prénom_10 TEXT,
    Voix_10 INTEGER,
    Voix_Ins_10 DECIMAL,
    Voix_Exp_10 DECIMAL,
    N_Panneau_11 INTEGER,
    Sexe_11 TEXT,
    Nom_11 TEXT,
    Prénom_11 TEXT,
    Voix_11 INTEGER,
    Voix_Ins_11 DECIMAL,
    Voix_Exp_11 DECIMAL
    )
''')

# Insérer les données dans la base de données
for _, row in df_filtered.iterrows():
    values = tuple(row)
    cursor.execute('''
        INSERT INTO election_results VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
         ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', values)

# Valider les modifications et fermer la connexion à la base de données
conn.commit()
conn.close()
