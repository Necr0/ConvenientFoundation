package convenientfoundation.gui.widget.label;

import convenientfoundation.gui.widget.IWidgetDrawable;
import convenientfoundation.util.GuiHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;

import java.util.function.Supplier;

public class DynamicLabel implements IWidgetDrawable {
    int posX,posY,color;
    boolean centered=false;
    Supplier<String> supplier;

    public DynamicLabel(int posX, int posY, Supplier<String> supplier){
        this(posX,posY,supplier,0xFFFFFF);
    }

    public DynamicLabel(int posX, int posY, Supplier<String> supplier, int color){
        this.posX=posX;
        this.posY=posY;
        this.color=color;
        this.supplier=supplier;
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return true;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        GuiHelper.getFontRenderer().drawString(getText(),posX-(centered?(getWidth()/2):0),posY,color);
        RenderHelper.enableStandardItemLighting();
    }

    public String getText(){
        return supplier.get();
    }

    @Override
    public int getX() {
        return posX - (centered?(getWidth()/2):0);
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public int getWidth() {
        return GuiHelper.getFontRenderer().getStringWidth(getText());
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    public DynamicLabel setCentered(boolean centered){
        this.centered=centered;
        return this;
    }
}