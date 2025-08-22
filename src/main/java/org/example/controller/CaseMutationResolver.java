package org.example.controller;

import org.example.model.Case;
import org.example.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CaseMutationResolver {

    @Autowired
    private CaseRepository caseRepository;

    @MutationMapping
    public Boolean saveCase(@Argument("input") Case input) {
        try {
            caseRepository.save(input);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
