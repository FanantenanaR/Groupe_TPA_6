# Importation des données dans MongoDB
## Lancement de mongoDB
Si vous utilisez la VM, mongodb se lancera automatiquement.

## Importation des données 
Pour importer les données, copiez les fichiers csv dans le repertoire source du VM. Puis vérifier si ils y sont avec 
```bash
ls /vagrant
```
Allez dans le dossier des données puis lancez les commandes suivantes:
* Pour importer `Clients_5.csv`, `Clients_13.csv`
```bash
mongoimport --type csv --headerline --db groupe6 --collection client --file Clients_N.csv
```

* Pour importer `Marketing.csv`
```bash
mongoimport --type csv --headerline --db groupe6 --collection marketing --file Marketing.csv
```
