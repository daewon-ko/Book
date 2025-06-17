package chapter1.item1;

import java.util.Set;

public class Settings {
    private boolean useAutoSteering;
    private boolean useABS;
    private Difficulty difficulty;
    private Settings() {
    }

    private static final Settings SETTINGS = new Settings();

    public static Settings getSettings() {
        return SETTINGS;
    }

}
