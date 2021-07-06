package service;

import af.CAF;
import af.PCAF;
import af.Preference;
import util.Statistics;

public class ImageService {

    public static PCAF image1(CAF caf, Statistics stats){
        // Können args andere args mit demselben claim angreifen? Ich denke nicht, nicht well-formed
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
}
