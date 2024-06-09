# Add Oracle NoSQL to Hive

## Lancement de Oracle NoSQL
Executer la commande suivante:
```bash
nohup java -Xmx256m -Xms256m -jar $KVHOME/lib/kvstore.jar kvlite -secure-config disable -root $KVROOT > kvstore.log 2>&1 &
```
Pour tester que Oracle NoSQL s'est bien lancé:
```bash
java -Xmx256m -Xms256m -jar $KVHOME/lib/kvstore.jar ping -host localhost -port 5000
```

## Vérification de la table 
Lancez la commande suivante:
```bash
java -Xmx256m -Xms256m -jar $KVHOME/lib/sql.jar -helper-hosts localhost:5000 -store kvstore
```
Puis vérifier la table `immatriculation` 
## Création de la table externe
```SQL
CREATE EXTERNAL TABLE IMMATRICULATION_ORACLENOSQL_EXTERN (
    immatriculation STRING,
    marque STRING,
    nom STRING,
    puissance INT,
    longueur STRING,
    nbPlaces INT,
    nbPortes INT,
    couleur STRING,
    occasion BOOLEAN,
    prix INT
)
STORED BY 'oracle.kv.hadoop.hive.table.TableStorageHandler'
TBLPROPERTIES (
    "oracle.kv.kvstore" = "kvstore",
    "oracle.kv.hosts" = "localhost:5000", 
    "oracle.kv.hadoop.hosts" = "localhost/127.0.0.1", 
    "oracle.kv.tableName" = "immatriculation"
);
```

```xml
    <property>
        <name>hive.metastore.uris</name>
        <value>thrift://localhost:9083</value>
        <description>Thrift URI for the remote metastore. Used by metastore client to connect to remote metastore.</description>
    </property>
```