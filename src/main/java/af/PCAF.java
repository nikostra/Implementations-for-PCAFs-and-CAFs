package af;

import java.util.List;

public class PCAF extends CAF{
    private List<Preference> preferences;

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }
}
