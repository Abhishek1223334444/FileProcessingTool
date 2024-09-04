package com.example.fileprocessingtool;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlFileProcessor implements FileProcessor {

    private JAXBContext jaxbContext;

    public XmlFileProcessor() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(Books.class);
    }

    @Override
    public List<String> readFile(File file) throws FileProcessingException {
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Books books = (Books) unmarshaller.unmarshal(file);
            return books.getBookTitles();  // Assuming a method to get book titles as a list of strings
        } catch (JAXBException e) {
            throw new FileProcessingException("Error reading XML file: " + file.getName(), e);
        }
    }

    @Override
    public void processFile(List<String> data) throws DataProcessingException {
        // Implement your data processing logic here
        data.forEach(title -> System.out.println("Processing book: " + title));
    }

    @Override
    public void writeFile(File file, List<String> data) throws FileProcessingException {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Books books = new Books();
            books.setBooksFromTitles(data); // Assuming a method to set books from titles

            marshaller.marshal(books, file);
        } catch (JAXBException e) {
            throw new FileProcessingException("Error writing XML file: " + file.getName(), e);
        }
    }
}
