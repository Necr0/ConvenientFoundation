package convenientfoundation.client.gui.widget;

import convenientfoundation.client.gui.IGui;
import net.minecraft.client.gui.GuiScreen;

public interface IWidgetKeyboardListener extends IWidget {
    <T extends GuiScreen & IGui> boolean onKey(T guiScreen, char typedChar, int keyCode);
}
