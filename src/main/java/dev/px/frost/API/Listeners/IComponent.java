package dev.px.frost.API.Listeners;

import java.io.IOException;

public interface IComponent {

    void render(int mouseX, int mouseY);

    void onClick(int mouseX, int mouseY, int button) throws IOException;

    void onRelease(int mouseX, int mouseY, int button);

    void onType(char typedChar, int keyCode) throws IOException;

}
