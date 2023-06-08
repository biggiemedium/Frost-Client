package dev.px.frost.api.Module;

public enum Type {

    COMBAT("Combat"),
    MISC("Miscellaneous"),
    PLAYER("Player"),
    RENDER("Render");

    String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
