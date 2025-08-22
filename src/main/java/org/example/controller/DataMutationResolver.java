package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.DataInput;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DataMutationResolver {
    private static final String FILE_PATH = "data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MutationMapping
    public Boolean putData(DataInput input) {
        try {
            Map<String, String> data = new HashMap<>();
            File file = new File(FILE_PATH);
            if (file.exists()) {
                data = objectMapper.readValue(file, Map.class);
            }
            data.put(input.getKey(), input.getValue());
            objectMapper.writeValue(file, data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

