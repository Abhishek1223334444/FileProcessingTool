package com.example.fileprocessingtool;

import java.io.File;
import java.util.List;

public interface FileProcessor {
    List<String> readFile(File file) throws FileProcessingException;
    void processFile(List<String> data) throws DataProcessingException;
    void writeFile(File file, List<String> data) throws FileProcessingException;
}
