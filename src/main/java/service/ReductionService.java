package service;

import af.Attack;
import af.CAF;
import af.PCAF;
import af.Preference;

public class ReductionService {

    public CAF reduction1(PCAF pcaf){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            }
        });
        caf.setArguments(pcaf.getArguments());
        return caf;
    }

    public CAF reduction2(PCAF pcaf){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker())) &&
                        pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(new Attack(attack.getDefender(), attack.getAttacker()));
            }
        });
        caf.setArguments(pcaf.getArguments());
        return caf;
    }

    public CAF reduction3(PCAF pcaf){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            }
        });
        caf.setArguments(pcaf.getArguments());

        return caf;
    }

    public CAF reduction4(PCAF pcaf){
        CAF caf = new CAF();
        pcaf.getAttacks().forEach(attack -> {
            if(!pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            } else if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker()))){
                caf.getAttacks().add(attack);
            }
            if(!pcaf.getAttacks().contains(new Attack(attack.getDefender(), attack.getAttacker())) &&
                    pcaf.getPreferences().contains(new Preference(attack.getDefender(), attack.getAttacker()))) {
                caf.getAttacks().add(new Attack(attack.getDefender(), attack.getAttacker()));
            }
        });
        caf.setArguments(pcaf.getArguments());

        return caf;

    }
}
