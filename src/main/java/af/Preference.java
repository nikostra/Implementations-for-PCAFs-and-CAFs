package af;

import java.util.Objects;

public class Preference {
    private Argument superior;
    private Argument inferior;

    public Preference(Argument superior, Argument inferior) {
        this.superior = superior;
        this.inferior = inferior;
    }

    public Argument getSuperior() {
        return superior;
    }

    public void setSuperior(Argument superior) {
        this.superior = superior;
    }

    public Argument getInferior() {
        return inferior;
    }

    public void setInferior(Argument inferior) {
        this.inferior = inferior;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "superior=" + superior +
                ", inferior=" + inferior +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return Objects.equals(superior, that.superior) && Objects.equals(inferior, that.inferior);
    }

    @Override
    public int hashCode() {
        return Objects.hash(superior, inferior);
    }
}
