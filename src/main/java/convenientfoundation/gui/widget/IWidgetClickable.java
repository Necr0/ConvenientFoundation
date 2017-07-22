package convenientfoundation.gui.widget;

import convenientfoundation.gui.IGui;
import net.minecraft.client.gui.GuiScreen;

public interface IWidgetClickable extends IWidget{
    default <T extends GuiScreen & IGui> boolean isClickable(T guiScreen){
        return isVisible();
    }
    <T extends GuiScreen & IGui> void onClicked(T guiScreen, int mouseX, int mouseY, int mouseButton);
}
