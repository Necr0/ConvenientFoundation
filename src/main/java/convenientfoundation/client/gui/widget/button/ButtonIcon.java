package convenientfoundation.client.gui.widget.button;

import convenientfoundation.libs.LibMod;
import convenientfoundation.client.gui.ImageResourceLocation;
import convenientfoundation.client.gui.widget.IWidget;
import convenientfoundation.client.gui.widget.IWidgetTooltip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.util.Rectangle;

import java.util.Collections;
import java.util.List;

public class ButtonIcon extends GuiButton implements IWidget,IWidgetTooltip {
	public static final ImageResourceLocation BUTTON_IDLE=new ImageResourceLocation(LibMod.MODID+":textures/gui/imageButtons.png", 0, 0, 18, 18);
	public static final ImageResourceLocation BUTTON_HOVER=new ImageResourceLocation(LibMod.MODID+":textures/gui/imageButtons.png", 0, 18, 18, 18);
	
	public ImageResourceLocation img;
	String tooltip;

	public ButtonIcon(int buttonId, ImageResourceLocation image, int x, int y) {
		super(buttonId, x, y, 16, 16, "");
		this.img=image;
	}

	public ButtonIcon(int buttonId, ImageResourceLocation image, String tooltip , int x, int y) {
		super(buttonId, x, y, 16, 16, "");
		this.img=image;
		this.tooltip=tooltip;
	}
	
	@Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
		this.hovered=new Rectangle(x, y, width, height).contains(mouseX, mouseY);
		drawButtonBackground(mc,mouseX,mouseY);
		mc.renderEngine.bindTexture(img);
		mc.currentScreen.drawTexturedModalRect(x+1, y+1, img.startX, img.startY, img.sizeX, img.sizeY);
    }
	
    public void drawButtonBackground(Minecraft mc, int mouseX, int mouseY)
    {
    	ImageResourceLocation background;
    	if(isMouseOver())
    		background=BUTTON_HOVER;
    	else
    		background=BUTTON_IDLE;
		mc.renderEngine.bindTexture(background);
		mc.currentScreen.drawTexturedModalRect(x, y, background.startX, background.startY, background.sizeX, background.sizeY);
    }

    @Override
    public boolean hasTooltip(GuiScreen guiScreen){
		return tooltip!=null;
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY){
		return Collections.singletonList(tooltip);
    }

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}
}
