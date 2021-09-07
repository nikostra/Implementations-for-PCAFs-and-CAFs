package service;

import af.Attack;
import af.CAF;
import af.PCAF;
import af.Preference;
import util.Statistics;

public class ReductionService {

    public CAF reduction1(PCAF pcaf, Statistics stats){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else {
                if(stats != null)
                    stats.incrementRemovedAttacks();
            }
        });
        caf.setArguments(pcaf.getArguments());
        return caf;
    }

    public CAF reduction2(PCAF pcaf, Statistics stats){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker())) &&
                        pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(new Attack(attack.getDefender(), attack.getAttacker()));
                if(stats != null){
                    stats.incrementAddedAttacks();
                    stats.incrementRemovedAttacks();
                }
            } else {
                if(stats != null)
                    stats.incrementRemovedAttacks();
            }
        });
        caf.setArguments(pcaf.getArguments());
        return caf;
    }

    public CAF reduction3(PCAF pcaf, Statistics stats){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else {
                if(stats != null)
                    stats.incrementRemovedAttacks();
            }
        });
        caf.setArguments(pcaf.getArguments());

        return caf;
    }

    public CAF reduction4(PCAF pcaf, Statistics stats){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else {
                if(stats != null)
                    stats.incrementRemovedAttacks();
            }
            if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker())) &&
                    pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))) {
                caf.getAttacks().add(new Attack(attack.getDefender(), attack.getAttacker()));
                if(stats != null)
                    stats.incrementAddedAttacks();
            }
        });
        caf.setArguments(pcaf.getArguments());

        return caf;
    }
}
