import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import sqlite3

results_df = pd.read_csv('../../data/2022_resultats-par-niveau-burvot-t2-france-entiere.csv', sep=';',
                         encoding='cp1252')

# Convertir la colonne 'Code du département' en chaîne de caractères
results_df['Code du département'] = results_df['Code du département'].astype(str)

# Filtrer les données pour garder uniquement celles relatives à la Loire-Atlantique et aux Alpes-Maritimes
dept_codes = ['44', '6']
df_filtered = results_df[results_df['Code du département'].isin(dept_codes)]
print(df_filtered['Code du département'].unique())
print(df_filtered.shape)
print(df_filtered.head())

print(df_filtered.columns.tolist())

# Établir une connexion à la base de données SQLite
conn = sqlite3.connect('../../data_output/bdd_election_2022_t2.db')
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
    Voix_Exp_2 DECIMAL
    )
''')

# Insérer les données dans la base de données
for _, row in df_filtered.iterrows():
    values = tuple(row)
    cursor.execute('''
        INSERT INTO election_results VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
         ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', values)

# Valider les modifications et fermer la connexion à la base de données
conn.commit()
conn.close()
