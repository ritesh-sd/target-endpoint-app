package org.example.service;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.ArangoCollection;
import com.arangodb.entity.DocumentCreateEntity;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.model.Case;
import org.springframework.stereotype.Service;

@Service
public class ArangoDBService {
    private ArangoDB arangoDB;
    private ArangoDatabase database;
    private ArangoCollection collection;

    private static final String DATABASE_NAME = "target";
    private static final String COLLECTION_NAME = "cases";
    private static final String HOST = "localhost";
    private static final int PORT = 8529;

    @PostConstruct
    public void init() {
        // Initialize ArangoDB connection with authentication
        arangoDB = new ArangoDB.Builder()
                .host(HOST, PORT)
                .user("root")
                .password("root")
                .build();

        // Get or create database
        if (!arangoDB.db(DATABASE_NAME).exists()) {
            arangoDB.createDatabase(DATABASE_NAME);
        }
        database = arangoDB.db(DATABASE_NAME);

        // Get or create collection
        if (!database.collection(COLLECTION_NAME).exists()) {
            database.createCollection(COLLECTION_NAME);
        }
        collection = database.collection(COLLECTION_NAME);
    }

    public DocumentCreateEntity<Void> saveCase(Case caseObj) {
        try {
            return collection.insertDocument(caseObj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save case to ArangoDB: " + e.getMessage(), e);
        }
    }

    @PreDestroy
    public void cleanup() {
        if (arangoDB != null) {
            arangoDB.shutdown();
        }
    }
}
