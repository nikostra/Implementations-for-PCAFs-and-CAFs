import af.PCAF;
import service.FileService;
import util.ProtocolException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws ProtocolException {
        if(args == null || args.length != 2){
            throw new IllegalArgumentException("Args don't have correct length");
        }
        try {
            PCAF pcaf = FileService.parseInput(args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
        }
    }
    // args: Reductions to perform (1,2,3,4) filepath to input, filepath to output?
    // assumptions: txt als input und erst args dann prefs und attacks, namen von  args und claims kann keine .() und
    // whitespace enthalten
    // Fragen: Java 15 ok? Große files nötig? Test files vorhanden?
}
