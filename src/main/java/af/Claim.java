package af;

import java.util.List;
import java.util.Objects;

public class Claim {

    private String name;
    private List<Argument> attacking;

    public Claim(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Argument> getAttacking() {
        return attacking;
    }

    public void setAttacking(List<Argument> attacking) {
        this.attacking = attacking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return name.equals(claim.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Claim{" +
                "name='" + name + '\'' +
                '}';
    }
}
