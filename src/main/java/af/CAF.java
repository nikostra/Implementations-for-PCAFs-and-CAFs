package af;

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
}
