package service;

import af.PCAF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileService {


    public static PCAF parseInput(String filepath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepath)).useDelimiter("\\.");
        while (scanner.hasNext()){
            String s = scanner.next().replaceAll("[\\n\\t ]", "");


        }
        return null;
    }
}
