package com.validity.monolithstarter.service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
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
        int maxPercentSimilarForDupe = 30;

		try {
            FileReader Data = new FileReader("../test-files/advanced.csv");
            parser = CSVParser.parse(Data, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            CSVRecord previous = null;
            ArrayList duplicates = new ArrayList<String>();

            for (CSVRecord csvRecord : parser) {

                response += AlgorithmHandler.csvRecordToJSON(csvRecord);

                if (previous != null && maxPercentSimilarForDupe >= AlgorithmHandler.levenshteinDuplicate(csvRecord, previous)){
                    response += "true\" };" ;
                    duplicates.add(previous.get(1));

                }
                else {
                    response += "false\" };";
                }
                previous = csvRecord;

            }
            System.out.print(duplicates.toString());

		}
		catch (Exception e) {
			System.out.print("Error " + e.toString());
		}

		return response;
    }
}
