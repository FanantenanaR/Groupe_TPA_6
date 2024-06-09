# Importations des données dans Oracle NoSQL
## Lancement de Oracle NoSQL
Pour lancez Oracle NoSQL dans la VM, executez la commande suivante:
```bash
nohup java -Xmx256m -Xms256m -jar $KVHOME/lib/kvstore.jar kvlite -secure-config disable -root $KVROOT > kvstore.log 2>&1 &
```

Vous pouvez tester si Oracle NoSQL est lancé avec 
```bash
java -Xmx256m -Xms256m -jar $KVHOME/lib/kvstore.jar ping -host localhost -port 5000
```

## Vérification des données
Les fichiers utilisés sont `Immatriculation.csv` et `Catalogue.csv`.

Avant d'importer, vérifier que les entêtes des fichiers sont commes les suivants:
* `Immatriculation.csv`
```csv
immatriculation,marque,nom,puissance,longueur,nbPlaces,nbPortes,couleur,occasion,prix
```
* `Catalogue.csv`
```
marque,nom,puissance,longueur,nbPlaces,nbPortes,couleur,occasion,prix
```
Vous pouvez utiliser la commande `head` pour appercevoir le début des fichiers cités. 

## Importation des données
Dans le présent projet contient les codes pour importer les données dans Oracle NoSQL.

Pour compiler les codes, lancez la commande suivante:
```bash
javac ***
```
Puis pour executer le code
```bash
java 
```