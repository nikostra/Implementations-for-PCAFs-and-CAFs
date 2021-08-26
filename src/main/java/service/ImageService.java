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
        // Wenn es dann noch nicht passt für alle problematischen attacks schauen ob es beim selben Claim wie vom Attacker
        // Attacken auf den Defender gibt und wenn ja, die reverse attacke zu der hinzufügen und diese entfernen
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

        problematic = VerificationService.verifyWellFormedness(pcaf);

        for (Attack problematicAtttack: problematic) {
            for (Attack attack: pcaf.getAttacks()) {
                if(attack.getAttacker().getClaim().equals(problematicAtttack.getAttacker().getClaim()) &&
                    attack.getDefender().equals(problematicAtttack.getDefender()))
                {
                    Attack reverse = new Attack(attack.getDefender(), attack.getAttacker());
                    pcaf.getAttacks().remove(attack);
                    pcaf.getAttacks().add(reverse);
                    pcaf.getPreferences().add(new Preference(attack.getAttacker(), attack.getDefender()));
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
