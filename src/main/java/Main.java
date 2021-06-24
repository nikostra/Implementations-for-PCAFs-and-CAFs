import af.CAF;
import af.PCAF;
import service.FileService;
import service.ReductionService;
import service.VerificationService;
import util.ProtocolException;
import util.VerificationException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /**
     *
     * @param args argument format: mandatory: (input filepath) (r1/r2/r3/r4 to select the reduction to perform)
     *             Other options in any order: -wfi (for well-formed checks of input) -wfo (for well-formed check
     *             of output) -vp (to verify preferences (transitive, irreflexive, asymmetric))
     *
     *             eg a.txt r1 -vp -wfo --> perform reduction 1 on a.txt and verify the preferences
     *             and perform a well-formed check on the output
     *
     * @throws ProtocolException if there is an error while parsing the input
     * @throws FileNotFoundException if the input file can not be found
     * @throws VerificationException if verification fails
     */
    public static void main(String[] args) throws ProtocolException, FileNotFoundException, VerificationException {
        if(args == null){

            throw new IllegalArgumentException("Illegal arguments, arguments cannot be empty");
        }
        FileService f = new FileService();
        PCAF pcaf = f.parseInput(args[0]);

        var argsList = Arrays.asList(args);
        argsList = new ArrayList<>(argsList);
        argsList.remove(0);

        if(argsList.contains("-vp")){
            VerificationService.verifyPreferences(pcaf);
            argsList.remove("-vp");
        }
        if(argsList.contains("-wfi")){

            if (VerificationService.verifyWellFormedness(pcaf)) {
                System.out.println("Input is well-formed");
            } else {
                System.out.println("Input is NOT well formed");
            }
            argsList.remove("-wfi");
        }

        ReductionService r = new ReductionService();
        String reduction = argsList.get(0);
        argsList.remove(0);
        CAF caf = switch (reduction) {
            case "r1" -> r.reduction1(pcaf);
            case "r2" -> r.reduction2(pcaf);
            case "r3" -> r.reduction3(pcaf);
            case "r4" -> r.reduction4(pcaf);
            default -> throw new IllegalArgumentException("Illegal reduction argument");
        };

        if(argsList.contains("-wfo")){
            if (VerificationService.verifyWellFormedness(caf)) {
                System.out.println("Output is well-formed");
            } else {
                System.out.println("Output is NOT well formed");
            }
            argsList.remove("-wfo");
        }

        System.out.println();
        System.out.println("Output CAF:");
        f.parseOutput(caf);
    }
}
