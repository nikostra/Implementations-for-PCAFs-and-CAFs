package service;

import af.Attack;
import af.CAF;
import af.PCAF;
import af.Preference;
import util.Statistics;
import util.VerificationException;

public class ImageService {

    public static PCAF image1(CAF caf, Statistics stats){
        PCAF pcaf = new PCAF();
        var problematic = VerificationService.verifyWellFormedness(caf);

        pcaf.setAttacks(caf.getAttacks());
        pcaf.setArguments(caf.getArguments());

        problematic.forEach(attack -> {
            pcaf.getPreferences().add(new Preference(attack.getDefender(), attack.getAttacker()));
            pcaf.getAttacks().add(attack);
            if(stats != null){
                stats.incrementAddedAttacks();
            }
        });
        return pcaf;
    }

    public static PCAF image3(CAF caf, Statistics stats) throws VerificationException{
        // Problematic Attacks und Preference werden hinzugefügt wenn es eine gegenattacke gibt.
        // Wenn nicht, dann ist das CAF nicht im Image der Reduktion

        PCAF pcaf = new PCAF();
        var problematic = VerificationService.verifyWellFormedness(caf);

        pcaf.setAttacks(caf.getAttacks());
        pcaf.setArguments(caf.getArguments());

        for (Attack attack : problematic) {
            if (!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker()))) {
                throw new VerificationException("");
            }
            pcaf.getPreferences().add(new Preference(attack.getDefender(), attack.getAttacker()));
            pcaf.getAttacks().add(attack);
            if (stats != null) {
                stats.incrementAddedAttacks();
            }
        }
        return pcaf;
    }

}
