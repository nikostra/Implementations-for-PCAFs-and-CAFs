package af;

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
}
