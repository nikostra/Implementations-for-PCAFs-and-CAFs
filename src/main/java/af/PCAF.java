package af;

import java.util.ArrayList;
import java.util.List;

public class PCAF extends CAF{
    private List<Preference> preferences;

    public PCAF() {
        super();
        preferences = new ArrayList<>();
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }
}
