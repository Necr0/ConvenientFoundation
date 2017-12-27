package convenientfoundation.client.gui.widget.energy;

import convenientfoundation.common.energy.EnergyRegistry;
import convenientfoundation.common.energy.EnergyStack;
import convenientfoundation.common.energy.capability.IEnergyBattery;
import convenientfoundation.client.gui.widget.IWidgetDrawable;
import convenientfoundation.client.gui.widget.IWidgetTooltip;
import convenientfoundation.libs.LibImages;
import convenientfoundation.libs.LibRegistries;
import convenientfoundation.util.GuiHelper;
import convenientfoundation.util.Helper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.List;

/**
 * Created by Necro on 8/28/2017.
 */
public class EnergyBatteryView implements IWidgetDrawable, IWidgetTooltip {

    public IEnergyBattery energyBattery;
    int posX,posY;

    public EnergyBatteryView(IEnergyBattery battery, int posX, int posY){
        this.energyBattery=battery;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public boolean hasTooltip(GuiScreen guiScreen) {
        return energyBattery.getEnergy()!=null;
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        return energyBattery.getEnergy().getTooltip(Helper.getClientWorld(),energyBattery.getCapacity());
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) { return true; }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        drawEnergy(guiScreen, posX, posY, energyBattery);
    }

    public void drawEnergy(GuiScreen guiScreen, int x, int y, IEnergyBattery battery){
        GuiHelper.renderIRL(guiScreen.mc,LibImages.ENERGY_BAR_BG,x,y);
        EnergyStack stack=battery.getEnergy();
        if (stack == null) {
            return;
        }

        TextureAtlasSprite energySprite = EnergyRegistry.getEnergySprite(stack.getType());
        guiScreen.mc.renderEngine.bindTexture(LibRegistries.ENERGY_TEXTURE_LOCATION);

        int pixels_height=(int)(((double)stack.getAmount()/battery.getCapacity())*58);
        int current_height=0;
        while(pixels_height>=16){
            pixels_height-=16;
            GuiHelper.renderTAS(energySprite,x+1,y+getHeight()-17-current_height,300);
            current_height+=16;
        }
        if(pixels_height>0)
            GuiHelper.renderMaskedTAS(energySprite,x+1,y+getHeight()-17-current_height,300,0,16-pixels_height,0,0);
    }

    @Override
    public int getX() { return posX; }

    @Override
    public int getY() { return posY; }

    @Override
    public int getWidth() { return 18; }

    @Override
    public int getHeight() { return 60; }

    @Override
    public boolean isVisible() { return true; }
}
