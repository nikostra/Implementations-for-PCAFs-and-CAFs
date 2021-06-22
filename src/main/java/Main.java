import af.CAF;
import af.PCAF;
import service.FileService;
import service.ReductionService;
import service.VerificationService;
import util.ProtocolException;
import util.VerificationException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws ProtocolException, FileNotFoundException, VerificationException {
        if(args == null || args.length != 2){
            throw new IllegalArgumentException("Illegal arguments");
        }
        FileService f = new FileService();
        PCAF pcaf = f.parseInput(args[1]);

        VerificationService.verifyPreferences(pcaf);
        VerificationService.verifyWellFormedness(pcaf);

        ReductionService r = new ReductionService();
        CAF caf = switch (args[0]) {
            case "1" -> r.reduction1(pcaf);
            case "2" -> r.reduction2(pcaf);
            case "3" -> r.reduction3(pcaf);
            case "4" -> r.reduction4(pcaf);
            default -> throw new IllegalArgumentException("Illegal reduction argument");
        };

        f.parseOutput(caf);
    }
}
