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
        }

        //transitive
        for (int i = 0; i < pcaf.getArguments().size(); i++) {
            for (int j = i + 1; j < pcaf.getArguments().size(); j++) {
                for (int k = j + 1; k < pcaf.getArguments().size(); k++) {
                    Argument a = pcaf.getArguments().get(i);
                    Argument b = pcaf.getArguments().get(j);
                    Argument c = pcaf.getArguments().get(k);

                    if (pcaf.getPreferences().contains(new Preference(b, a)) && pcaf.getPreferences().contains(new Preference(c, b)) && !pcaf.getPreferences().contains(new Preference(c, a)))
                        throw new VerificationException("Preferences not transitive. Preference missing: " + new Preference(c, a));
                    if (pcaf.getPreferences().contains(new Preference(c, a)) && pcaf.getPreferences().contains(new Preference(b, c)) && !pcaf.getPreferences().contains(new Preference(b, a)))
                        throw new VerificationException("Preferences not transitive. Preference missing: " + new Preference(b, a));
                    if (pcaf.getPreferences().contains(new Preference(a, b)) && pcaf.getPreferences().contains(new Preference(c, a)) && !pcaf.getPreferences().contains(new Preference(c, b)))
                        throw new VerificationException("Preferences not transitive. Preference missing: " + new Preference(c, b));
                    if (pcaf.getPreferences().contains(new Preference(a, c)) && pcaf.getPreferences().contains(new Preference(b, a)) && !pcaf.getPreferences().contains(new Preference(b, c)))
                        throw new VerificationException("Preferences not transitive. Preference missing: " + new Preference(b, c));
                    if (pcaf.getPreferences().contains(new Preference(b, c)) && pcaf.getPreferences().contains(new Preference(a, b)) && !pcaf.getPreferences().contains(new Preference(a, c)))
                        throw new VerificationException("Preferences not transitive. Preference missing: " + new Preference(a, c));
                    if (pcaf.getPreferences().contains(new Preference(c, b)) && pcaf.getPreferences().contains(new Preference(a, c)) && !pcaf.getPreferences().contains(new Preference(a, b)))
                        throw new VerificationException("Preferences not transitive. Preference missing: " + new Preference(a, b));
                }
            }
        }
    }

    public static void verifyPreferencesImage(PCAF pcaf) throws VerificationException {
        for (Preference preference : pcaf.getPreferences()) {
            //irreflexive
            if (preference.getInferior().equals(preference.getSuperior()))
                throw new VerificationException("Preference not irreflexive: " + preference);

            //asymmetric
            if (pcaf.getPreferences().contains(new Preference(preference.getInferior(), preference.getSuperior())))
                throw new VerificationException("Preference not asymmetric: " + preference);
        }

        //transitive
        for (int i = 0; i < pcaf.getArguments().size(); i++) {
            for (int j = i + 1; j < pcaf.getArguments().size(); j++) {
                for (int k = j + 1; k < pcaf.getArguments().size(); k++) {
                    Argument a = pcaf.getArguments().get(i);
                    Argument b = pcaf.getArguments().get(j);
                    Argument c = pcaf.getArguments().get(k);

                    if (pcaf.getPreferences().contains(new Preference(b, a)) && pcaf.getPreferences().contains(new Preference(c, b)) && !pcaf.getPreferences().contains(new Preference(c, a)))
                        pcaf.getPreferences().add(new Preference(c, a));
                    if (pcaf.getPreferences().contains(new Preference(c, a)) && pcaf.getPreferences().contains(new Preference(b, c)) && !pcaf.getPreferences().contains(new Preference(b, a)))
                        pcaf.getPreferences().add(new Preference(b, a));
                    if (pcaf.getPreferences().contains(new Preference(a, b)) && pcaf.getPreferences().contains(new Preference(c, a)) && !pcaf.getPreferences().contains(new Preference(c, b)))
                        pcaf.getPreferences().add(new Preference(c, b));
                    if (pcaf.getPreferences().contains(new Preference(a, c)) && pcaf.getPreferences().contains(new Preference(b, a)) && !pcaf.getPreferences().contains(new Preference(b, c)))
                        pcaf.getPreferences().add(new Preference(b, c));
                    if (pcaf.getPreferences().contains(new Preference(b, c)) && pcaf.getPreferences().contains(new Preference(a, b)) && !pcaf.getPreferences().contains(new Preference(a, c)))
                        pcaf.getPreferences().add(new Preference(a, c));
                    if (pcaf.getPreferences().contains(new Preference(c, b)) && pcaf.getPreferences().contains(new Preference(a, c)) && !pcaf.getPreferences().contains(new Preference(a, b)))
                        pcaf.getPreferences().add(new Preference(a, b));
                }
            }
        }
    }


    public static List<Attack> verifyWellFormedness(CAF caf) {
        // loop durch alle attacks, speicher jeweils ab welche claim und arg welches arg angreift.
        // Am Ende wird für jedes gespeicherte arg überprüft ob es die selben angreift wie der claim.
        List<Claim> claims = new ArrayList<>();
        List<Argument> args = new ArrayList<>();

        for (Attack attack : caf.getAttacks()) {
            var index = claims.indexOf(attack.getAttacker().getClaim());
            if (index != -1) {
                var claim = claims.get(index);
                if (!claim.getAttacking().contains(attack.getDefender())) {
                    claim.getAttacking().add(attack.getDefender());
                }
            } else {
                var claim = attack.getAttacker().getClaim();
                claim.setAttacking(new ArrayList<>());
                claim.getAttacking().add(attack.getDefender());
                claims.add(claim);
            }
            index = args.indexOf(attack.getAttacker());
            if (index != -1) {
                var arg = args.get(index);
                if (!arg.getAttacking().contains(attack.getDefender()))
                    arg.getAttacking().add(attack.getDefender());
            } else {
                var arg = attack.getAttacker();
                arg.setAttacking(new ArrayList<>());
                arg.getAttacking().add(attack.getDefender());
                args.add(arg);
            }
        }

        var problematicAttacks = new ArrayList<Attack>();

        for (Argument argument : caf.getArguments()) {
            var index = claims.indexOf(argument.getClaim());
            if (!args.contains(argument) && index != -1) {
                claims.get(index).getAttacking().forEach(arg -> problematicAttacks.add(new Attack(argument, arg)));
            } else if (index != -1) {
                if (!new HashSet<>(argument.getAttacking()).equals(new HashSet<>(claims.get(index).getAttacking()))) {
                    claims.get(index).getAttacking().forEach(arg -> {
                        if(!argument.getAttacking().contains(arg))
                            problematicAttacks.add(new Attack(argument, arg));
                    });
                }
            }
        }
        return problematicAttacks;
    }
}
