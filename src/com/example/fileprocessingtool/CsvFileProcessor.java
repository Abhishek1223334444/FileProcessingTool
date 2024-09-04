package com.example.fileprocessingtool;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileProcessor implements FileProcessor {

    @Override
    public List<String> readFile(File file) throws FileProcessingException {
        List<String> data = new ArrayList<>();
        try (FileReader reader = new FileReader(file);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                data.add(record.toString());
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error reading CSV file: " + file.getName(), e);
        }
        return data;
    }

    @Override
    public void processFile(List<String> data) throws DataProcessingException {
        // Implement your data processing logic here
    }

    @Override
    public void writeFile(File file, List<String> data) throws FileProcessingException {
        // Implement your file writing logic here
    }
}
