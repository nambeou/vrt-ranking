package com.vietnamroller.ranking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extraction {
    public static void main(String[] args) throws IOException {
        var dataPath = "/Users/mac-Z28NHOAN/Downloads/km-kh.csv";
        var referencePath = "/Users/mac-Z28NHOAN/Downloads/en-kh.csv";

        var lines = readCsv(dataPath);
        var reference = readCsv(referencePath);
        var extraction = new ArrayList<String>();
        for (var line : lines) {
            if (!reference.contains(line)) {
                extraction.add(line);
            }
        }
        System.out.println(extraction);

    }


    static List<String> readCsv(String file) throws IOException {
        List<String> result = new ArrayList();
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            result.add(line);
        }
        return result;
    }
}
