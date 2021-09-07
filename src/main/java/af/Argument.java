package af;

import java.util.List;
import java.util.Objects;

public class Argument {
    private String name;
    private Claim claim;
    private List<Argument> attacking;

    public Argument(String name, Claim claim) {
        this.name = name;
        this.claim = claim;
    }

    public Argument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public List<Argument> getAttacking() {
        return attacking;
    }

    public void setAttacking(List<Argument> attacking) {
        this.attacking = attacking;
    }

    @Override
    public String toString() {
        return "Argument{" +
                "name='" + name + '\'' + (claim != null ? ", claim=" + claim : "") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return name.equals(argument.name);
    }
}
