package af;

import util.ProtocolException;

import java.util.ArrayList;
import java.util.List;

public class CAF {
    List<Argument> arguments;
    List<Attack> attacks;

    public CAF() {
        arguments = new ArrayList<>();
        attacks = new ArrayList<>();
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public void addAttack(String attacker, String defender) throws ProtocolException {
        Argument att, def;
        try {
            att = arguments.get(arguments.indexOf(new Argument(attacker)));

        } catch (IndexOutOfBoundsException e){
            throw new ProtocolException("Illegal Attack added, Argument " + attacker + " does not exist");
        }
        try{
            def = arguments.get(arguments.indexOf(new Argument(defender)));
        } catch (IndexOutOfBoundsException e){
            throw new ProtocolException("Illegal Attack added, Argument " + defender + " does not exist");
        }
        attacks.add(new Attack(att, def));
    }

    @Override
    public String toString() {
        return "CAF{" +
                "arguments=" + arguments +
                ", attacks=" + attacks +
                '}';
    }
}
