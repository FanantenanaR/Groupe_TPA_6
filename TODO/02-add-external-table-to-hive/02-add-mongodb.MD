# DB Link Mongodb to Hive
## Préparation et vérification
Après avoir importer les fichiers dans hdfs (revoir [ici](../01-import-data/01-import-mongodb.MD)), vous pouvez vérifier les données avec:
* `client`
```mongo
db.client.find({}).count();
```
* `marketing`
```mongo
db.client.find({}).count();
```
## Création de table externe
Pour ce faire, exécutez les deux scripts suivants: 
* Collection `client`:
```SQL
CREATE EXTERNAL TABLE CLIENT_MONGO_EXTERN
(
    id                 STRING,
    age                STRING,
    sexe               STRING,
    taux               STRING,
    situationFamiliale STRING,
    nbEnfantsAcharge   STRING,
    deuxieme_voiture   STRING,
    immatriculation    STRING
)
    STORED BY 'com.mongodb.hadoop.hive.MongoStorageHandler'
        WITH SERDEPROPERTIES ('mongo.columns.mapping' = '{"id":"_id", "deuxieme_voiture":"2eme voiture", "situationFamiliale": "situationFamiliale", "nbEnfantsAcharge": "nbEnfantsAcharge"}')
    TBLPROPERTIES ('mongo.uri' = 'mongodb://localhost:27017/groupe6.client');
```
* Collection `marketing`
```SQL
CREATE OR REPLACE EXTERNAL TABLE MARKETING_MONGO_EXTERN
(
    id                 STRING,
    age                STRING,
    sexe               STRING,
    taux               STRING,
    situationFamiliale STRING,
    nbEnfantsAcharge   STRING,
    deuxieme_voiture   STRING
)
    STORED BY 'com.mongodb.hadoop.hive.MongoStorageHandler'
        WITH SERDEPROPERTIES ('mongo.columns.mapping' = '{"id":"_id", "deuxieme_voiture":"2eme voiture"}')
    TBLPROPERTIES ('mongo.uri' = 'mongodb://localhost:27017/groupe6.marketing');
```

## Test
Vous pouvez tester en comptant par exemple le nombre de ligne dans chaque tables:
* Table `CLIENT_MONGO_EXTERN` 
```SQL
SELECT COUNT(id) FROM CLIENT_MONGO_EXTERN
```
* Table  `MARKETING_MONGO_EXTERN`
```SQL
SELECT COUNT(id) FROM MARKETING_MONGO_EXTERN
```