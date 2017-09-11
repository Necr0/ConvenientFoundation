package convenientfoundation.gui.widget.label;

import convenientfoundation.gui.widget.IWidgetDrawable;
import convenientfoundation.util.GuiHelper;
import net.minecraft.client.gui.GuiScreen;

public class Label implements IWidgetDrawable {
    int posX,posY,color;
    String text;

    public Label(int posX, int posY, String text ){
        this(posX,posY,text,0xFFFFFF);
    }

    public Label(int posX, int posY, String text, int color ){
        this.text=text;
        this.color=color;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return true;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        guiScreen.drawString(GuiHelper.getFontRenderer(),text,posX,posY,color);
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public int getWidth() {
        return GuiHelper.getFontRenderer().getStringWidth(text);
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
