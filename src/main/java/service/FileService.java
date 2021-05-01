package service;

import af.Argument;
import af.Claim;
import af.PCAF;
import util.ProtocolException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileService {


    public static PCAF parseInput(String filepath) throws FileNotFoundException, ProtocolException {
        Scanner scanner = new Scanner(new File(filepath)).useDelimiter("\\.");
        boolean args = true;
        PCAF pcaf = new PCAF();
        while (scanner.hasNext()){
            String s = scanner.next().replaceAll("[\\n\\t ]", "");
            if(args){
                if(s.startsWith("arg")){
                    String[] parts = s.split("\\(");
                    parts = parts[0].split(",");
                    pcaf.getArguments().add(new Argument(parts[0], new Claim(parts[1])));
                }
                else if (s.startsWith("att") || s.startsWith("pref")){
                    args = false;
                } else {
                    throw new ProtocolException("Protocol Error");
                }

            }else {

            }
        }
        return null;
    }
}
