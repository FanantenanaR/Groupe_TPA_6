# Préparation du fichier csv
Tout d'abord, il faut rectifier l'entete du fichier csv comme suite:
```csv
ID,marque_modele,bonus_malus,rejets_co2_g_per_km,cout_energie
```
Ensuite, créer un dossier dans HDFS:
```bash 
hdfs dfs -mkdir tpa
```
Puis ajoutez le fichier `CO2.csv` dans le dossier `/tpa` de HDFS avec:
```bash
hdfs dfs -put $MYTPHOME/CO2.csv /tpa
```
Vous pouvez vérifier le contenue du dossier `/tpa` avec 
```bash
hdfs dfs -ls /tpa
```
# Création de la table externe
Pour cela, nous devons nous connecter sur HIVE avec le client `Beeline`. 
## Lancement de Hive
Les commandes pour lancer Hive sont:
```bash
nohup hive --service metastore > /dev/null &
```
et
```bash
nohup hiveserver2 > /dev/null &
```

## Connection avec Beeline
Pour utilisez Beeline, entrez dans le terminal
```bash
beeline
```
Puis 
```bash
!connect jdbc:hive2://localhost:10000
```
## Création de la table externe
Pour créer la table externe dans Hive:
```sql
CREATE EXTERNAL TABLE  CO2_HDFS_EXT  (ID INT, marque_modele STRING, bonus_malus STRING,  rejets_co2_g_per_km STRING, cout_energie  string)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE LOCATION 'hdfs:/tpa';
```
