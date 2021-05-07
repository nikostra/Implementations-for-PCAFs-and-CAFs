package af;

import java.util.Objects;

public class Attack {
    private Argument attacker;
    private Argument defender;

    public Attack(Argument attacker, Argument defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public Argument getAttacker() {
        return attacker;
    }

    public void setAttacker(Argument attacker) {
        this.attacker = attacker;
    }

    public Argument getDefender() {
        return defender;
    }

    public void setDefender(Argument defender) {
        this.defender = defender;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "attacker=" + attacker +
                ", defender=" + defender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attack attack = (Attack) o;
        return Objects.equals(attacker, attack.attacker) && Objects.equals(defender, attack.defender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attacker, defender);
    }
}
