package util;

public class Statistics {

    private int arguments;
    private int claims;
    private int preferences;
    private int cafAttacks;
    private int pcafAttacks;
    private int removedAttacks;
    private int addedAttacks;

    public int getArguments() {
        return arguments;
    }

    public int getClaims() {
        return claims;
    }

    public int getPreferences() {
        return preferences;
    }

    public int getCafAttacks() {
        return cafAttacks;
    }

    public int getPcafAttacks() {
        return pcafAttacks;
    }

    public int getRemovedAttacks() {
        return removedAttacks;
    }

    public int getAddedAttacks() {
        return addedAttacks;
    }

    public void setArguments(int arguments) {
        this.arguments = arguments;
    }

    public void setClaims(int claims) {
        this.claims = claims;
    }

    public void setPreferences(int preferences) {
        this.preferences = preferences;
    }

    public void setCafAttacks(int cafAttacks) {
        this.cafAttacks = cafAttacks;
    }

    public void setPcafAttacks(int pcafAttacks) {
        this.pcafAttacks = pcafAttacks;
    }

    public void incrementAddedAttacks(){
        addedAttacks++;
    }

    public void incrementRemovedAttacks(){
        removedAttacks++;
    }

    @Override
    public String toString() {
        return "Statistics: " +
                "Arguments = " + arguments +
                ", Preferences = " + preferences +
                ", CafAttacks = " + cafAttacks +
                ", PcafAttacks = " + pcafAttacks +
                ", Removed Attacks = " + removedAttacks +
                ", Added Attacks = " + addedAttacks;
    }
}
