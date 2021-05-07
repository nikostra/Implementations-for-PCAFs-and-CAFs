package service;

import af.*;
import util.ProtocolException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileService {


    public PCAF parseInput(String filepath) throws FileNotFoundException, ProtocolException {
        Scanner scanner = new Scanner(new File(filepath)).useDelimiter("\\.");
        boolean args = true;
        PCAF pcaf = new PCAF();
        while (scanner.hasNext()){
            String s = scanner.next().replaceAll("\\s+", "");
            if(args){
                if(s.startsWith("arg")){
                    String[] parts = getParts(s);
                    pcaf.getArguments().add(new Argument(parts[0], new Claim(parts[1])));
                }
                else if (s.startsWith("att") || s.startsWith("pref")){
                    args = false;
                    if(s.startsWith("att")){
                        String[] parts = getParts(s);
                        pcaf.getAttacks().add(new Attack(new Argument(parts[0]), new Argument(parts[1])));
                    } else if (s.startsWith("pref")){
                        String[] parts = getParts(s);
                        pcaf.getPreferences().add(new Preference(new Argument(parts[0]), new Argument(parts[1])));
                    }
                } else {
                    throw new ProtocolException("Protocol Error");
                }
            }else {
                if(s.startsWith("att")){
                    String[] parts = getParts(s);
                    pcaf.getAttacks().add(new Attack(new Argument(parts[0]), new Argument(parts[1])));
                } else if (s.startsWith("pref")){
                    String[] parts = getParts(s);
                    pcaf.getPreferences().add(new Preference(new Argument(parts[0]), new Argument(parts[1])));
                } else if (s.equals("")){
                    break;
                } else {
                    throw new ProtocolException("Protocol Error");
                }
            }
        }
        return pcaf;
    }

    private String[] getParts(String s){
        String[] parts = s.split("\\(");
        parts = parts[1].split(",");
        parts[1] = parts[1].replaceAll("\\)","");
        return parts;
    }
}
