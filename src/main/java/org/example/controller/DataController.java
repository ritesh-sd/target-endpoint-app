package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.DataInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private static final String FILE_PATH = "data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

}
