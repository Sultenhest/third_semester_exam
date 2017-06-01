package III_designPatterns;

public final class Singleton {
    private static Singleton instance;
    private static String    value    = "some value";

    private Singleton() { }

    private static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static String getValue() {
        return value;
    }
}
