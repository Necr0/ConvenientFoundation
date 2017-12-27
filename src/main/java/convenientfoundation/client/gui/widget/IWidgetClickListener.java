package convenientfoundation.client.gui.widget;

import convenientfoundation.client.gui.IGui;
import net.minecraft.client.gui.GuiScreen;

public interface IWidgetClickListener extends IWidget {
    <T extends GuiScreen & IGui> void onClick(T guiScreen, int mouseX, int mouseY, int mouseButton);
}
