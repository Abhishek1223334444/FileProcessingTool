package com.example.fileprocessingtool;

import com.example.fileprocessingtool.DataProcessingException;
import com.example.fileprocessingtool.FileProcessingException;
import com.example.fileprocessingtool.FileProcessor;
import com.example.fileprocessingtool.XmlFileProcessor;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FileProcessingToolApplication {
    public static void main(String[] args) {
        try {
            URL resource = FileProcessingToolApplication.class.getClassLoader().getResource("data.xml");
            if (resource == null) {
                throw new FileNotFoundException("Resource not found: data.xml");
            }
            String decodedPath = URLDecoder.decode(resource.getFile(), StandardCharsets.UTF_8.name());
            File file = new File(decodedPath);

            FileProcessor processor = new XmlFileProcessor();
            var data = processor.readFile(file);
            processor.processFile(data);
            processor.writeFile(new File("processed_data.xml"), data);
        } catch (FileProcessingException | DataProcessingException | FileNotFoundException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error decoding file path: " + e.getMessage());
            e.printStackTrace();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
