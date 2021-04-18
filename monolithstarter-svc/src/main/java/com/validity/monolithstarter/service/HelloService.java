package com.validity.monolithstarter.service;

import org.springframework.stereotype.Service;
import java.io.*;
import org.apache.commons.csv.*;

@Service
public class HelloService {
    public String getHelloMessage() {
		return parseCSV();
        //return "Hello from the server!";
    }

    public String parseCSV() {
        CSVParser parser;
        String response = "";

		try {
            FileReader Data = new FileReader("../test-files/normal.csv");
            parser = CSVParser.parse(Data, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            CSVRecord previous = null;

            for (CSVRecord csvRecord : parser) {

                response += csvRecord.get(1);

                if (previous != null && AlgorithmHandler.levenshteinDuplicate(csvRecord, previous, 5)){
                    response += " [D], ";
                }
                else {
                    response += ", ";
                }
                previous = csvRecord;

            }

		}
		catch (Exception e) {
			System.out.print("Error " + e.toString());
		}

		return response;
    }
}
