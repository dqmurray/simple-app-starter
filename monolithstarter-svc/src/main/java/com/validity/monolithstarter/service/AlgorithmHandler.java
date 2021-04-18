package com.validity.monolithstarter.service;

import org.springframework.stereotype.Service;
import java.io.*;
import org.apache.commons.csv.*;
import java.util.*;

public class AlgorithmHandler {
    public static boolean levenshteinDuplicate(CSVRecord record1, CSVRecord record2, int distanceMax){
        Iterator<String> r1 = record1.iterator();
        Iterator<String> r2 = record2.iterator();
        String e1;
        String e2;
        int distance = 0;
        while (r1.hasNext() && r2.hasNext()){
            e1 = r1.next();
            e2 = r2.next();
            if (e1.equals(e2)){
                distance = distance;
            }
            else {
                distance++;
            }
        }
        return distance <= distanceMax;
    }
}
