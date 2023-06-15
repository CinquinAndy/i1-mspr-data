import pandas as pd
import sqlite3

# Lire le fichier Excel
df = pd.read_excel('../data_input/Presidentielle_2017_Resultats_Tour_2_c.xls', sheet_name='Circo. Leg. Tour 2')

# Filtrer sur les départements 44 et 6 que nous avons choisis
df_filtered = df[(df['Code du département'] == 44) | (df['Code du département'] == 6)]

# Établir une connexion à la base de données SQLite
conn = sqlite3.connect('../data_output/bdd_election_2017_t2_44_6.db')
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
        pourcentage_voix_exprimes_2 REAL
    )
''')

# Insérer les données dans la base de données
for _, row in df_filtered.iterrows():
    values = tuple(row)
    cursor.execute('''
        INSERT INTO resultats VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', values)

# Valider les modifications et fermer la connexion à la base de données
conn.commit()
conn.close()
