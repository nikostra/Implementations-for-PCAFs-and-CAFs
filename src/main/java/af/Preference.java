package af;

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
}
