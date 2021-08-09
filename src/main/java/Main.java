import af.CAF;
import af.PCAF;
import service.FileService;
import service.ImageService;
import service.ReductionService;
import service.VerificationService;
import util.ProtocolException;
import util.Statistics;
import util.VerificationException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /**
     *
     * @param args argument format: mandatory: <input filepath> <r1/r2/r3/r4/i1> to select the reduction to perform
     *             Other options in any order: -wfi (for well-formed checks of input) -wfo (for well-formed check
     *             of output) -vp (to verify preferences (transitive, irreflexive, asymmetric))
     *             -stats for detailed statistics about the structures
     *
     *             if i1 is selected, the input is a CAF and image checking is performed, to find a suitable PCAF. For
     *             this option no options are working, except for -stats
     *
     *             eg "a.txt r1 -vp -wfo" --> perform reduction 1 on a.txt, verify the preferences
     *             and perform a well-formed check on the output
     *
     * @throws ProtocolException if there is an error while parsing the input
     * @throws FileNotFoundException if the input file can not be found
     * @throws VerificationException if verification fails
     */
    public static void main(String[] args) throws ProtocolException, FileNotFoundException, VerificationException {
        if (args == null) {
            throw new IllegalArgumentException("Illegal arguments, arguments cannot be empty");
        }
        FileService f = new FileService();
        PCAF pcaf = f.parseInput(args[0]);

        var argsList = Arrays.asList(args);
        argsList = new ArrayList<>(argsList);
        argsList.remove(0);

        Statistics stats = null;

        if (argsList.contains("r1") || argsList.contains("r2") || argsList.contains("r3") || argsList.contains("r4")) {
            if(argsList.contains("-stats")){
                stats = new Statistics();
                stats.setArguments(pcaf.getArguments().size());
                stats.setPcafAttacks(pcaf.getAttacks().size());
                stats.setPreferences(pcaf.getPreferences().size());
            }

            if (argsList.contains("-vp")) {
                VerificationService.verifyPreferences(pcaf);
                argsList.remove("-vp");
            }
            if (argsList.contains("-wfi")) {
                System.out.println();
                var wfi = VerificationService.verifyWellFormedness(pcaf);
                if (wfi.isEmpty()) {
                    System.out.println("Input is well-formed");
                } else {
                    System.out.println("Input is NOT well formed");
                    System.out.println("Problematic Pairs: ");
                    System.out.println(wfi);
                }
                argsList.remove("-wfi");
            }

            ReductionService r = new ReductionService();
            String reduction = argsList.get(0);
            argsList.remove(0);
            CAF caf = switch (reduction) {
                case "r1" -> r.reduction1(pcaf, stats);
                case "r2" -> r.reduction2(pcaf, stats);
                case "r3" -> r.reduction3(pcaf, stats);
                case "r4" -> r.reduction4(pcaf, stats);
                default -> throw new IllegalArgumentException("Illegal reduction argument");
            };

            if (argsList.contains("-wfo")) {
                System.out.println();
                var wfo = VerificationService.verifyWellFormedness(caf);
                if (wfo.isEmpty()) {
                    System.out.println("Output is well-formed");
                } else {
                    System.out.println("Output is NOT well formed");
                    System.out.println("Problematic Pairs: ");
                    System.out.println(wfo);
                }
                argsList.remove("-wfo");
            }

            if(stats != null) {
                stats.setCafAttacks(caf.getAttacks().size());
                System.out.println(stats);
            }

            System.out.println();
            System.out.println("Output CAF:");
            f.parseOutput(caf);

        } else if(argsList.contains("i1") || argsList.contains("i3")) {
            if (argsList.contains("-stats")) {
                stats = new Statistics();
                stats.setArguments(pcaf.getArguments().size());
                stats.setCafAttacks(pcaf.getAttacks().size());
            }

            if (argsList.contains("i1")) {
                PCAF imagePcaf = ImageService.image1(pcaf, stats);

                try {
                    VerificationService.verifyPreferencesImage(imagePcaf);
                } catch (VerificationException e) {
                    System.out.println("CAF NOT in image of reduction 1, resulting Preferences not valid.");
                    System.out.println(e.getMessage());

                    f.parseOutput(imagePcaf);

                    System.exit(0);
                }

                if (stats != null) {
                    stats.setPreferences(imagePcaf.getPreferences().size());
                    stats.setPcafAttacks(imagePcaf.getAttacks().size());
                    System.out.println(stats);
                }

                System.out.println("CAF is contained in image of reduction 1.");
                System.out.println("Possible PCAF:");
                f.parseOutput(imagePcaf);

            } else if(argsList.contains("i3")){
                PCAF imagePcaf = new PCAF();
                try {
                    imagePcaf = ImageService.image3(pcaf, stats);
                } catch (VerificationException e){
                    System.out.println("CAF NOT in image of reduction 3, missing attack");
                    System.exit(0);
                }

                try {
                    VerificationService.verifyPreferencesImage(imagePcaf);
                } catch (VerificationException e) {
                    System.out.println("CAF NOT in image of reduction 3, resulting Preferences not valid.");
                    System.out.println(e.getMessage());
                    f.parseOutput(imagePcaf);
                    System.exit(0);
                }
                if (stats != null) {
                    stats.setPreferences(imagePcaf.getPreferences().size());
                    stats.setPcafAttacks(imagePcaf.getAttacks().size());
                    System.out.println(stats);
                }
                System.out.println("CAF is contained in image of reduction 3.");
                System.out.println("Possible PCAF:");
                f.parseOutput(imagePcaf);
            }
        }
    }
}
