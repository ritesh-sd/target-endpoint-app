package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Case {
    private String caseId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assignedTo;
    private String createdDate;
    private String updatedDate;
}
