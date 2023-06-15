import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

results_df = pd.read_csv('resultats-par-niveau-burvot-t1-france-entiere.csv', sep=';', encoding='latin-1',
                         )
print(results_df.head())

# Graphiques d'analyse de corrélation
sns.heatmap(results_df.corr(), annot=True, cmap='coolwarm', linewidths=0.2, annot_kws={'size': 10}, fmt='.2f')
plt.show()

# autres matrice de corrélation

sns.pairplot(results_df)
plt.show()

#
# plt.hist(results_df['Abstentions'], bins=10)
# plt.title('Histogramme de l\'âge des passagers')
# plt.xlabel('Âge')
# plt.ylabel('Nombre d\'observations')
# plt.show()


#
# sns.countplot(data=results_df, x='Nom')
# plt.title('Nombre de passagers dans chaque classe')
# plt.xlabel('Classe')
# plt.ylabel('Nombre de passagers')
# plt.show()
#
# sns.scatterplot(data=results_df, x='Nom', y='Votants', hue='Survived')
# plt.title('Nuage de points de l\'âge et du tarif payé')
# plt.show()


#
# results_df.groupby(['Sex', 'Pclass']).size().unstack().plot(kind='bar', stacked=True)
# plt.title('Répartition des passagers par sexe et par classe')
# plt.xlabel('Sexe')
# plt.ylabel('Nombre de passagers')
# plt.show()
#
# sns.violinplot(data=results_df, x='Sex', y='Age', hue='Pclass', split=True)
# plt.title('Distribution de l\'âge des passagers par sexe et par classe')
# plt.show()
#
# sns.boxplot(data=results_df, x='Survived', y='Age')
# plt.title('Distribution de l\'âge des passagers en fonction de leur survie')
# plt.show()
#
# survived_count = results_df['Survived'].sum()
# print('Nombre de passagers ayant survécu:', survived_count)
#
# survival_rate = results_df['Survived'].mean()
# print('Taux de survie global:', survival_rate)
#
# survival_by_sex = pd.crosstab(results_df['Survived'], results_df['Sex'])
# print(survival_by_sex)
#
# survival_by_class = pd.crosstab(results_df['Survived'], results_df['Pclass'])
# print(survival_by_class)
#
# survivors = results_df[results_df['Survived'] == 1]
# survivors_by_age = pd.cut(survivors['Age'], bins=[0, 18, 30, 50, 80])
# survivors_by_sex_and_age = pd.crosstab(survivors_by_age, survivors['Sex'])
# survivors_by_sex_and_age.plot(kind='bar', stacked=True)
# plt.title('Répartition des survivants par âge et par sexe')
# plt.xlabel('Âge')
# plt.ylabel('Nombre de survivants')
# plt.show()
#
# survivors_by_class_and_sex = pd.crosstab(survivors['Pclass'], survivors['Sex'])
# survivors_by_class_and_sex.plot(kind='bar', stacked=True)
# plt.title('Répartition des survivants par classe et par sexe')
# plt.xlabel('Classe')
# plt.ylabel('Nombre de survivants')
# plt.show()
