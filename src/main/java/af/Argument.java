package af;

public class Argument {
    private String name;
    private Claim claim;

    public Argument(String name, Claim claim) {
        this.name = name;
        this.claim = claim;
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
}
