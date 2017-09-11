package convenientfoundation.gui.widget;

import convenientfoundation.capabilities.heat.IHeatVessel;
import convenientfoundation.gui.IGui;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.GuiHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class HeatDisplay implements IWidgetDrawable, IWidgetTooltip {
    int posX,posY;
    boolean centered=true;
    IHeatVessel accumulator;

    public HeatDisplay(int posX, int posY, IHeatVessel accumulator){
        this.posX=posX;
        this.posY=posY;
        this.accumulator=accumulator;
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return true;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        GuiHelper.getFontRenderer().drawString(getText(),posX-(centered?(getWidth()/2):0),posY,getColor());
        RenderHelper.enableStandardItemLighting();
    }

    public double getPercentage(){
        if(!accumulator.canOverheat())
            return 0d;
        return Math.max(0d,Math.min(1.0d,accumulator.getHeatLevel()/accumulator.getCriticalHeat()));
    }

    public int getColor(){
        if(getPercentage()<=.5d){
            return MathHelper.hsvToRGB(1/3F, 1.0F, .75F);
        }else{
            return MathHelper.hsvToRGB((float) ((1-(getPercentage()*2d-1d))/3d), 1.0F, .75F+(float)((getPercentage()-.5)*.3));
        }
    }

    public String getText(){
        String text=I18n.format("unit."+LibMod.MODID+":hu.suffix",accumulator.getHeatLevel());
        if(getPercentage()>=.85)
            text+="!";
        if(getPercentage()>=.90)
            text+="!";
        if(getPercentage()>=.95)
            text+="!";
        return text;
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

    @Override
    public <T extends GuiScreen & IGui> boolean hasTooltip(T guiScreen) {
        return true;
    }

    @Override
    public <T extends GuiScreen & IGui> List<String> getTooltip(T guiScreen, float partialTicks, int mouseX, int mouseY) {
        ArrayList<String> ret=new ArrayList<>();
        if(!accumulator.canOverheat()){
            ret.add(I18n.format("unit."+LibMod.MODID+":heatunits.suffix",accumulator.getHeatLevel()));
        }else{
            ret.add(I18n.format("unit."+LibMod.MODID+":heatunits/critical.suffix",accumulator.getHeatLevel(),accumulator.getCriticalHeat()));
        }
        return ret;
    }

    public HeatDisplay setCentered(boolean centered){
        this.centered=centered;
        return this;
    }
}