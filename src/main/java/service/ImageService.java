package service;

import af.Attack;
import af.CAF;
import af.PCAF;
import af.Preference;
import util.Statistics;
import util.VerificationException;

public class ImageService {

    public static PCAF image1(CAF caf, Statistics stats) {
        PCAF pcaf = new PCAF();
        var problematic = VerificationService.verifyWellFormedness(caf);

        pcaf.setAttacks(caf.getAttacks());
        pcaf.setArguments(caf.getArguments());

        problematic.forEach(attack -> {
            pcaf.getPreferences().add(new Preference(attack.getDefender(), attack.getAttacker()));
            pcaf.getAttacks().add(attack);
            if (stats != null) {
                stats.incrementAddedAttacks();
            }
        });
        return pcaf;
    }

    public static PCAF image2(CAF caf, Statistics stats) throws VerificationException {
        //Problematische Attacks werden durchsucht und alle wo es die reverse attacke gibt, werden hinzugefügt und
        // die reverse Attacke entfernt. Dann ein well formed-check ob es eh passt.
        PCAF pcaf = new PCAF();
        var problematic = VerificationService.verifyWellFormedness(caf);

        pcaf.setAttacks(caf.getAttacks());
        pcaf.setArguments(caf.getArguments());

        for (Attack attack : problematic) {
            Attack reverse = new Attack(attack.getDefender(), attack.getAttacker());
            if(pcaf.getAttacks().remove(reverse)){
                pcaf.getPreferences().add(new Preference(attack.getDefender(), attack.getAttacker()));
                pcaf.getAttacks().add(attack);
                if (stats != null) {
                    stats.incrementAddedAttacks();
                    stats.incrementRemovedAttacks();
                }
            }
        }

        if(VerificationService.verifyWellFormedness(pcaf).isEmpty())
            return pcaf;
        else
            throw new VerificationException("CAF not in image of reduction 3");
    }

    public static PCAF image3(CAF caf, Statistics stats) throws VerificationException {
        // Problematic Attacks und Preference werden hinzugefügt wenn es eine gegenattacke gibt.
        // Wenn nicht, dann ist das CAF nicht im Image der Reduktion

        PCAF pcaf = new PCAF();
        var problematic = VerificationService.verifyWellFormedness(caf);

        pcaf.setAttacks(caf.getAttacks());
        pcaf.setArguments(caf.getArguments());

        for (Attack attack : problematic) {
            if (!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker()))) {
                System.out.println("Missing Attack: " + new Attack(attack.getAttacker(), attack.getDefender()));
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
