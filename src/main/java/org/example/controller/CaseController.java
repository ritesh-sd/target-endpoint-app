package org.example.controller;

import com.arangodb.entity.DocumentCreateEntity;
import org.example.model.Case;
import org.example.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    @Autowired
    private CaseRepository caseRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCase(@RequestBody Case caseObj) {
        try {
            // Set timestamps if not provided
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (caseObj.getCreatedDate() == null || caseObj.getCreatedDate().isEmpty()) {
                caseObj.setCreatedDate(currentTime);
            }
            if (caseObj.getUpdatedDate() == null || caseObj.getUpdatedDate().isEmpty()) {
                caseObj.setUpdatedDate(currentTime);
            }

            // Save to ArangoDB using repository
            DocumentCreateEntity<Void> result = caseRepository.save(caseObj);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Case saved successfully to ArangoDB");
            response.put("documentKey", result.getKey());
            response.put("documentId", result.getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error saving case to ArangoDB: " + e.getMessage());

            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}
