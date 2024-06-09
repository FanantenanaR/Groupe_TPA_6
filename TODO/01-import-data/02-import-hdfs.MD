# Importion des données dans HDFS
## Lancement de Hadoop HDFS
Pour lancer Hadoop HDFS dans le VM, executez la commande suivante:
```bash 
start-dfs.sh
```
```bash 
start-yarn.sh
```

## Préparation des données
Avant d'importer les données dans HDFS, vérifier que les entêtes du fichier concerné (`CO2.csv`) est comme suite:
```csv
ID,marque_modele,bonus_malus,rejets_co2_g_per_km,cout_energie
```

## Préparation de l'environnement (Optionnel mais recommandée)
Pour plus de praticité, déclarer la variable suivante:
```bash
export DATAHOME=/vagrant/Groupe_TPA_6_csv/
```

## Importation des données 
Tout d'abord, il faut rectifier l'entete du fichier csv comme suite:
* `CO2.csv`
```csv
ID,marque_modele,bonus_malus,rejets_co2_g_per_km,cout_energie
```
* `Immatriculations.csv`
```csv
immatriculation,marque,nom,puissance,longueur,nbPlaces,nbPortes,couleur,occasion,prix
```

Ensuite, créer un dossier dans HDFS pour chaque fichier csv:
* Pour CO2:
```bash 
hdfs dfs -mkdir tpa
```
* Pour Immatriculation:
```bash
hdfs dfs -mkdir tpaimmatriculation
```

Puis ajoutez le fichier `CO2.csv` dans le dossier `tpa` de HDFS avec:
```bash
hdfs dfs -put $DATAHOME/CO2.csv tpa/CO2.csv
```
Et `Immatriculation.csv` dans le dossier `tpaimmatriculation`:
```bash
hdfs dfs -put $DATAHOME/Immatriculations.csv tpaimmatriculation/Immatriculations.csv
```
