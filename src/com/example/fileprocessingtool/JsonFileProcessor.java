package com.example.fileprocessingtool;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileProcessor implements FileProcessor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<String> readFile(File file) throws FileProcessingException {
        try {
            return objectMapper.readValue(file, List.class);
        } catch (IOException e) {
            throw new FileProcessingException("Error reading JSON file: " + file.getName(), e);
        }
    }

    @Override
    public void processFile(List<String> data) throws DataProcessingException {
        // Implement your data processing logic here
    }

    @Override
    public void writeFile(File file, List<String> data) throws FileProcessingException {
        try {
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            throw new FileProcessingException("Error writing JSON file: " + file.getName(), e);
        }
    }
}
