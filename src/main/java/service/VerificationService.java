package service;

import af.*;
import util.VerificationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VerificationService {

    public static void verifyPreferences(PCAF pcaf) throws VerificationException {
        for (Preference preference : pcaf.getPreferences()) {
            //irreflexive
            if (preference.getInferior().equals(preference.getSuperior()))
                throw new VerificationException("Preference not irreflexive: " + preference);

            //asymmetric
            if (pcaf.getPreferences().contains(new Preference(preference.getInferior(), preference.getSuperior())))
                throw new VerificationException("Preference not asymmetric: " + preference);

            //transitive

        }
    }

    public static void verifyWellFormedness(CAF caf){
        // loop durch alle attacks, speicher jeweils ab welche claim und arg welches arg angreift. Am Ende wird für jedes gespeicherte arg überprüft ob es die selben angreift wie der claim
        List<Claim> claims = new ArrayList<>();
        List<Argument> args = new ArrayList<>();

        for (Attack attack : caf.getAttacks()) {
            var index = claims.indexOf(attack.getAttacker().getClaim());
            if(index != -1){
                var claim = claims.get(index);
                if(!claim.getAttacking().contains(attack.getDefender())) {
                    claim.getAttacking().add(attack.getDefender());
                }
            } else {
                var claim = attack.getAttacker().getClaim();
                claim.setAttacking(new ArrayList<>());
                claim.getAttacking().add(attack.getDefender());
                claims.add(claim);
            }
            index = args.indexOf(attack.getAttacker());
            if(index != -1){
                var arg = args.get(index);
                if(!arg.getAttacking().contains(attack.getDefender()))
                    arg.getAttacking().add(attack.getDefender());
            } else {
                var arg = attack.getAttacker();
                arg.setAttacking(new ArrayList<>());
                arg.getAttacking().add(attack.getDefender());
                args.add(arg);
            }
        }

        boolean wellFormed = true;

        for (Argument argument : caf.getArguments()) {
            if(!args.contains(argument) && claims.contains(argument.getClaim())) {
                wellFormed = false;
                break;
            }
            var index = claims.indexOf(argument.getClaim());
            if(index != -1){
                if (!new HashSet<>(argument.getAttacking()).equals(new HashSet<>(claims.get(index).getAttacking()))) {
                    wellFormed = false;
                    break;
                }
            }
        }
        System.out.println("Claims: ");
        System.out.println(claims);
        System.out.println("Args: ");
        System.out.println(args);
        System.out.println("Well-formed? " + wellFormed);
        System.out.println();
    }
}
