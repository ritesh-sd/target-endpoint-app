package org.example.repository;

import com.arangodb.entity.DocumentCreateEntity;
import org.example.model.Case;
import org.example.service.ArangoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CaseRepository {

    @Autowired
    private ArangoDBService arangoDBService;

    public DocumentCreateEntity<Void> save(Case caseObj) {
        return arangoDBService.saveCase(caseObj);
    }
}
