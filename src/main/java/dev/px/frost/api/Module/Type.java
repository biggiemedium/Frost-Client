package dev.px.frost.API.Module;

public enum Type {

    CLIENT("Client"),
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
