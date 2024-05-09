package hello;

import oracle.kv.*;
import oracle.kv.table.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Tpa {
    private final KVStore store;
    private final String myTpPath = "/vagrant/TpBigData";
    private final String tableImmatriculation = "Immatriculation";
    private final String tableCatalogue = "Catalogue";

    public static void main(String args[]) {
        try {
            Tpa tpa = new Tpa(args);
            tpa.initTPATablesAndData();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    Tpa(String[] argv) {
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";
        store = KVStoreFactory.getStore(new KVStoreConfig(storeName, hostName + ":" + hostPort));
    }

    private void displayResult(oracle.kv.StatementResult result, String statement) {
    System.out.println("===========================");
    if (result.isSuccessful()) {
        System.out.println("Statement was successful:\n\t" + statement);
        System.out.println("Results:\n\t" + result.getInfo());
    } else if (result.isCancelled()) {
        System.out.println("Statement was cancelled:\n\t" + statement);
    } else {
        if (result.isDone()) {
            System.out.println("Statement failed:\n\t" + statement);
            System.out.println("Problem:\n\t" + result.getErrorMessage());
        } else {
            System.out.println("Statement in progress:\n\t" + statement);
            System.out.println("Status:\n\t" + result.getInfo());
        }
    }
}


    public void initTPATablesAndData() {
        // dropTableImmatriculation();
        // createTableImmatriculation();
        // loadImmatriculationDataFromFile(myTpPath+"/tpnosql/hello/Immatriculations.csv");

        dropTableCatalogue();
        createTableCatalogue();
        loadCatalogueDataFromFile(myTpPath+"/tpnosql/hello/Catalogue.csv");
    }

    public void dropTableImmatriculation() {
        String statement = "drop table " + tableImmatriculation;
        executeDDL(statement);
    }

    public void dropTableCatalogue() {
        String statement = "drop table " + tableCatalogue;
        executeDDL(statement);
    }

    public void createTableImmatriculation() {
    String statement = "CREATE TABLE Immatriculation (" +
            "immatriculation STRING, " +
            "marque STRING, " +
            "nom STRING, " +
            "puissance INTEGER, " +
            "longueur STRING, " +
            "nbPlaces INTEGER, " +
            "nbPortes INTEGER, " +
            "couleur STRING, " +
            "occasion BOOLEAN, " +
            "prix STRING, " + 
            "PRIMARY KEY(immatriculation))";
    executeDDL(statement);
}

public void createTableCatalogue() {
    String statement = "CREATE TABLE Catalogue (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "marque STRING, " +
            "nom STRING, " +
            "puissance INTEGER, " +
            "longueur STRING, " +
            "nbPlaces INTEGER, " +
            "nbPortes INTEGER, " +
            "couleur STRING, " +
            "occasion BOOLEAN, " +
            "prix STRING, " +
            "PRIMARY KEY(id))";
    executeDDL(statement);
}

  public void executeDDL(String statement) {
    TableAPI tableAPI = store.getTableAPI();
    try {
        // Correcting the type of the result variable to match the return type of executeSync
        oracle.kv.StatementResult result = store.executeSync(statement);
        displayResult(result, statement);
    } catch (IllegalArgumentException | FaultException e) {
        System.out.println("Error executing statement: " + e.getMessage());
    }
}

    private void insertImmatriculationRow(String immatriculation, String marque, String nom, int puissance, String longueur, int nbPlaces, int nbPortes, String couleur, boolean occasion, String prix) {
        System.out.println("**************************** Dans : insertImmatriculationRow ***************************");

        try {
        TableAPI tableAPI = store.getTableAPI();
        // Assurez-vous que le nom de la table est correct et correspond à celui utilisé lors de sa création.
        Table tableImmatriculation = tableAPI.getTable("Immatriculation");
        if (tableImmatriculation == null) {
        throw new IllegalStateException("Table 'Immatriculation' not found");
        }

        // Créer une nouvelle ligne pour la table Immatriculation.
        Row immatriculationRow = tableImmatriculation.createRow();

        // Remplir les colonnes de la ligne avec les données fournies.
        immatriculationRow.put("immatriculation", immatriculation);
        immatriculationRow.put("marque", marque);
        immatriculationRow.put("nom", nom);
        immatriculationRow.put("puissance", puissance);
        immatriculationRow.put("longueur", longueur);
        immatriculationRow.put("nbPlaces", nbPlaces);
        immatriculationRow.put("nbPortes", nbPortes);
        immatriculationRow.put("couleur", couleur);
        immatriculationRow.put("occasion", occasion);
        immatriculationRow.put("prix", prix);

        // Écrire la ligne dans la table.
        Version putIfAbsentVersion = tableAPI.putIfAbsent(immatriculationRow,null,null);

        if (putIfAbsentVersion != null) {
            System.out.println("Immatriculation row inserted successfully.");
        } else {
            System.out.println("Immatriculation already exists.");
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
    }

    private void insertCatalogueRow(String marque, String nom, int puissance, String longueur, int nbPlaces, int nbPortes, String couleur, boolean occasion, String prix) {
        System.out.println("**************************** Dans : insertCatalogueRow ***************************");

        try {
        TableAPI tableAPI = store.getTableAPI();
        // Assurez-vous que le nom de la table est correct et correspond à celui utilisé lors de sa création.
        Table tableCatalogue = tableAPI.getTable("Catalogue");
        if (tableCatalogue == null) {
        throw new IllegalStateException("Table 'Catalogue' not found");
        }

        // Créer une nouvelle ligne pour la table Immatriculation.
        Row catalogueRow = tableCatalogue.createRow();

        // Remplir les colonnes de la ligne avec les données fournies.
        catalogueRow.put("marque", marque);
        catalogueRow.put("nom", nom);
        catalogueRow.put("puissance", puissance);
        catalogueRow.put("longueur", longueur);
        catalogueRow.put("nbPlaces", nbPlaces);
        catalogueRow.put("nbPortes", nbPortes);
        catalogueRow.put("couleur", couleur);
        catalogueRow.put("occasion", occasion);
        catalogueRow.put("prix", prix);

        // Écrire la ligne dans la table.
        Version putIfAbsentVersion = tableAPI.putIfAbsent(catalogueRow,null,null);

        if (putIfAbsentVersion != null) {
            System.out.println("Catalogue row inserted successfully.");
        } else {
            System.out.println("Catalogue already exists.");
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
    }

 void loadImmatriculationDataFromFile(String immatriculationDataFileName) {
        InputStreamReader ipsr = null;  // Initialiser à null
        BufferedReader br = null;
        InputStream ips = null;        // Initialiser à null
        String ligne;
        System.out.println("**************************** Dans : loadImmatriculationDataFromFile ***************************");

        try {
            ips = new FileInputStream(immatriculationDataFileName);
            ipsr = new InputStreamReader(ips);
            br = new BufferedReader(ipsr);

            // Ignorer la première ligne si elle contient des en-têtes de colonne
            br.readLine();

            // Parcourir le fichier ligne par ligne
            while ((ligne = br.readLine()) != null) {
                String[] immatriculationRecord = ligne.split(",");

                // Extraction des données de chaque ligne
                String immatriculation = immatriculationRecord[0];
                String marque = immatriculationRecord[1];
                String nom = immatriculationRecord[2];
                int puissance = Integer.parseInt(immatriculationRecord[3]);
                String longueur = immatriculationRecord[4];
                int nbPlaces = Integer.parseInt(immatriculationRecord[5]);
                int nbPortes = Integer.parseInt(immatriculationRecord[6]);
                String couleur = immatriculationRecord[7];
                boolean occasion = Boolean.parseBoolean(immatriculationRecord[8]);
               String prix = immatriculationRecord[9];

                // Affichage des données extraites (ou appel à une méthode pour insérer les données)
                // System.out.println("Immatriculation=" + immatriculation + " Marque=" + marque + " Nom=" + nom +
                //                    " Puissance=" + puissance + " Longueur=" + longueur + " NbPlaces=" + nbPlaces +
                //                    " NbPortes=" + nbPortes + " Couleur=" + couleur + " Occasion=" + occasion +
                //                    " Prix=" + prix);

                insertImmatriculationRow(immatriculation, marque, nom, puissance, longueur, nbPlaces, nbPortes, couleur, occasion, prix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (ipsr != null) ipsr.close();
                if (ips != null) ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void loadCatalogueDataFromFile(String catalogueDataFileName) {
        InputStreamReader ipsr = null;  // Initialiser à null
        BufferedReader br = null;
        InputStream ips = null;        // Initialiser à null
        String ligne;
        System.out.println("**************************** Dans : loadCatalogueDataFromFile ***************************");

        try {
            ips = new FileInputStream(catalogueDataFileName);
            ipsr = new InputStreamReader(ips);
            br = new BufferedReader(ipsr);

            // Ignorer la première ligne si elle contient des en-têtes de colonne
            br.readLine();

            // Parcourir le fichier ligne par ligne
            while ((ligne = br.readLine()) != null) {
                String[] catalogueRecord = ligne.split(",");

                // Extraction des données de chaque ligne
                String marque = catalogueRecord[1];
                String nom = catalogueRecord[2];
                int puissance = Integer.parseInt(catalogueRecord[3]);
                String longueur = catalogueRecord[4];
                int nbPlaces = Integer.parseInt(catalogueRecord[5]);
                int nbPortes = Integer.parseInt(catalogueRecord[6]);
                String couleur = catalogueRecord[7];
                boolean occasion = Boolean.parseBoolean(catalogueRecord[8]);
               String prix = catalogueRecord[9];

                insertCatalogueRow(marque, nom, puissance, longueur, nbPlaces, nbPortes, couleur, occasion, prix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (ipsr != null) ipsr.close();
                if (ips != null) ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}