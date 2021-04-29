import af.PCAF;
import service.FileService;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        if(args == null || args.length != 2){
            throw new IllegalArgumentException("Args don't have correct length");
        }
        try {
            PCAF pcaf = FileService.parseInput(args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
        }
        System.out.println(args[0] + args[1]);
    }
    // args: Reductions to perform (1,2,3,4) filepath to input, filepath to output?
    // assumptions: txt als input und erst args dann prefs und attacks
}
