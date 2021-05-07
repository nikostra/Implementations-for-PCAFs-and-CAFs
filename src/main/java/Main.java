import af.CAF;
import af.PCAF;
import service.FileService;
import service.ReductionService;
import util.ProtocolException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws ProtocolException, FileNotFoundException {
        if(args == null || args.length != 2){
            throw new IllegalArgumentException("Args don't have correct length");
        }
        FileService f = new FileService();
        PCAF pcaf = f.parseInput(args[1]);

        System.out.println(pcaf);

        ReductionService r = new ReductionService();
        CAF caf = switch (args[0]) {
            case "1" -> r.reduction1(pcaf);
            case "2" -> r.reduction2(pcaf);
            case "3" -> r.reduction3(pcaf);
            case "4" -> r.reduction4(pcaf);
            default -> throw new ProtocolException("Illegal reduction argument");
        };

        System.out.println(caf);
    }
}
