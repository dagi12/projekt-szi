package pl.edu.amu.wmi.projekt_szi.decision;

public enum LevelledDecision {
    UNKNOWN(-1), NO(0), LIGHT(1), MEDIUM(2), HEAVY(3);

    private final int value;

    LevelledDecision(int value) {
        this.value = value;
    }

    public static LevelledDecision getEnumFromInt(int param) {
        switch (param) {
            case 0:
                return NO;
            case 1:
                return LIGHT;
            case 2:
                return MEDIUM;
            case 3:
                return HEAVY;
            case -1:
                return UNKNOWN;
        }
        return param < -1 ? NO : HEAVY;
    }

}
