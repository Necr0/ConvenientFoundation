package convenientfoundation.gui.widget;

import convenientfoundation.gui.IGui;
import net.minecraft.client.gui.GuiScreen;

public interface IWidgetClickListener extends IWidget {
    <T extends GuiScreen & IGui> void onClick(T guiScreen, int mouseX, int mouseY, int mouseButton);
}
