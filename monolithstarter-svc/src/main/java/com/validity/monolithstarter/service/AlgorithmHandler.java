package com.validity.monolithstarter.service;

import org.springframework.stereotype.Service;
import java.io.*;
import org.apache.commons.csv.*;
import java.util.*;

public class AlgorithmHandler {
    public static double levenshteinDuplicate(CSVRecord record1, CSVRecord record2){
        Iterator<String> r1 = record1.iterator();
        Iterator<String> r2 = record2.iterator();
        String e1 = r1.next();
        String e2 = r2.next();
        double bad = 0;
        double total = 0;

        if (e1.equals(e2))
            System.out.println(e1);

        while (r1.hasNext() && r2.hasNext()){
            e1 = r1.next();
            e2 = r2.next();
            if (e1.equals(e2)){
                total += 1;
            } else if (e1.equals("") || e2.equals("")){

            }
            else {
                bad += 1;
                total += 1;
            }
        }
        return bad/total * 100;
    }

    //Creates the most of the JSON as a string
    public static String csvRecordToJSON(CSVRecord csvRecord){
        String response =
        "{ \"id\":\"" + csvRecord.get(0)
        + "\", \"first_name\":\"" + csvRecord.get(1)
        + "\", \"last_name\":\"" + csvRecord.get(2)
        + "\", \"company\":\"" + csvRecord.get(3)
        + "\", \"email\":\"" + csvRecord.get(4)
        + "\", \"address1\":\"" + csvRecord.get(5)
        + "\", \"address2\":\"" + csvRecord.get(6)
        + "\", \"zip\":\"" + csvRecord.get(7)
        + "\", \"city\":\"" + csvRecord.get(8)
        + "\", \"state_long\":\"" + csvRecord.get(9)
        + "\", \"state\":\""+ csvRecord.get(10)
        + "\", \"phone\":\""+ csvRecord.get(11)
        + "\", \"duplicate\":\"";
        return response;
    }
}
