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

# Importion des données dans HDFS
## Lancement de Hadoop HDFS
Pour lancer Hadoop HDFS dans le VM, executez la commande suivante:
```bash 
start-dfs.sh
```

## Importation des données 
Pour importer les données, copiez les fichiers csv dans le repertoire source du VM. Puis vérifier leur existence avec 
```bash
ls /vagrant
```
Allez dans le dossier des données puis lancez la commande suivante:
```bash
hadoop fs -put CO2.csv
```
Vous pouvez vérifier le fichier avec
```bash
hadoop fs -ls
```

# Importations des données dans Oracle NoSQL
## Lancement de Oracle NoSQL
Pour lancez Oracle NoSQL dans la VM, executez la commande suivante:
```bash
nohup java -Xmx64m -Xms64m -jar $KVHOME/lib/kvstore.jar kvlite -secure-config disable -root $KVROOT &
```

## Importation des données
Nous allons utilisez les fichiers `Immatriculation.csv` et `Catalogue.csv`.

Attribut | Type | Description | Domaine de valeurs
Immatriculation | caractères | Numéro unique d’immatriculation du véhicule | Texte au format « 9999 AA 99 »
Marque | caractères | Nom de la marque du véhicule | Audi, BMW, Dacia, Daihatsu, Fiat, Ford, Honda, Hyundaï, Jaguar, Kia, Lancia, Mercedes , Mini, Nissan, Peugeot, Renault, Saab, Seat, Skoda, Volkswagen, Volvo
Nom | caractères | Nom du modèle de véhicule | S80 T6, Touran 2.0 FSI, Polo 1.2 6V, New Beatle 1.8, Golf 2.0 FSI, Superb 2.8 V6, Toledo 1.6, 9.3 1.8T, Vel Satis 3.5 V6, Megane 2.0 16V, Laguna 2.0T, Espace 2.0T, 1007 1.4, Primera 1.6, Maxima 3.0 V6, Almera 1.8, Copper 1.6 16V, S500, A200, Ypsilon 1.4 16V, Picanto 1.1, X-Type 2.5 V6, Matrix 1.6 FR-V 1.7, Mondeo 1.8, Croma 2.2, Cuore 1.0, Logan 1.6 MPI, M5, 120i, A3 2.0 FSI, A2 1.4
Puissance | | Puissance en chevaux Din | [55, 507]
Longueur | | Catégorie de longueur | courte, moyenne, longue, très longue
NbPlaces | numérique | Nombre de places | [5, 7]
NbPortes | numérique | Nombre de portes | [3, 5]
Couleur | catégoriel | Couleur | blanc, bleu, gris, noir, rouge
Occasion | booléen | Véhicule d’occasion ? | true, false
Prix | numérique | Prix de vente en euros | [7500, 101300]