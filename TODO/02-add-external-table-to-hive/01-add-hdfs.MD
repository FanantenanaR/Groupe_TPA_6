# Création de DBLink entre HDFS et Hive
## Préparation du fichier csv
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
hdfs dfs -put $MYTPHOME/CO2.csv tpa/CO2.csv
```
Et `Immatriculation.csv` dans le dossier `tpaimmatriculation`:
```bash
hdfs dfs -put $MYTPHOME/Immatriculations.csv tpaimmatriculation/Immatriculations.csv
```

Vous pouvez vérifier le contenue du dossier `tpa` avec 
```bash
hdfs dfs -ls tpa
```
et `tpaimmatriculation` avec
```bash
hdfs dfs -ls tpaimmatriculation
```

## Création des tables externes
Pour cela, nous devons nous connecter sur HIVE avec le client `Beeline`. 
### Lancement de Hive
Les commandes pour lancer Hive sont:
```bash
nohup hive --service metastore > /dev/null &
```
et
```bash
nohup hiveserver2 > /dev/null &
```
Attendez 3-5 mn pour s'assurer que Hive s'est lancé entièrement.
### Connection avec Beeline
Pour utilisez Beeline, entrez dans le terminal
```bash
beeline
```
Puis 
```bash
!connect jdbc:hive2://localhost:10000
```
### Création de la table externe
Pour créer la table externe dans Hive:
* Pour `CO2`
```sql
CREATE EXTERNAL TABLE CO2_HDFS_EXT
(
    ID                  INT,
    marque_modele       STRING,
    bonus_malus         STRING,
    rejets_co2_g_per_km STRING,
    cout_energie        string
)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
    STORED AS TEXTFILE LOCATION 'hdfs:///user/vagrant/tpa/'
    TBLPROPERTIES (
        "skip.header.line.count" = "1"
    );
```
* Pour `Immatriculation`
```SQL
CREATE EXTERNAL TABLE IMMATRICULATION_HDFS_EXTERN
(
    immatriculation STRING,
    marque          STRING,
    nom             STRING,
    puissance       INT,
    longueur        STRING,
    nbPlaces        INT,
    nbPortes        INT,
    couleur         STRING,
    occasion        BOOLEAN,
    prix            STRING
)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
    STORED AS TEXTFILE LOCATION 'hdfs:///user/vagrant/tpaimmatriculation/'
    TBLPROPERTIES (
        "skip.header.line.count" = "1"
    );
```

Vous pouvez vérifier ensuite avec:
* CO2
```SQL
SELECT COUNT(*) FROM CO2_HDFS_EXTERN
```
* Immatriculation
```SQL
SELECT COUNT(*) FROM IMMATRICULATION_HDFS_EXTERN
```