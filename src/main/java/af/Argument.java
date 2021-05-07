package af;

import java.util.Objects;

public class Argument {
    private String name;
    private Claim claim;

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

    @Override
    public String toString() {
        return "Argument{" +
                "name='" + name + '\'' +
                ", claim=" + claim +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return name.equals(argument.name); // && Objects.equals(claim, argument.claim); TODO think about this
    }
}
