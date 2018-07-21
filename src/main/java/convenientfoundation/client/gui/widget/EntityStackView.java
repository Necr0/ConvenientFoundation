package convenientfoundation.client.gui.widget;

import convenientfoundation.common.entity.stack.EntityStack;
import convenientfoundation.common.entity.stack.EntityTypeRegistry;
import convenientfoundation.libs.LibRegistries;
import convenientfoundation.util.GuiHelper;
import convenientfoundation.util.Helper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by Necro on 8/28/2017.
 */
public class EntityStackView implements IWidgetDrawable, IWidgetTooltip {

    public EntityStack entityStack;
    int posX,posY;

    public EntityStackView(EntityStack stack, int posX, int posY){
        this.entityStack=stack;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public boolean hasTooltip(GuiScreen guiScreen) {
        return entityStack!=null;
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        return entityStack.getTooltip(Helper.getClientWorld());
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return entityStack!=null;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        drawEntity(guiScreen, posX, posY, entityStack);
    }

    public void drawEntity(GuiScreen guiScreen, int x, int y, EntityStack stack){
        if (stack == null) {
            return;
        }
        GlStateManager.pushAttrib();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();

        TextureAtlasSprite entitySprite = EntityTypeRegistry.getEntitySprite(stack.getType());
        guiScreen.mc.renderEngine.bindTexture(LibRegistries.ENTITY_TEXTURE_LOCATION);
        GuiHelper.renderTAS(entitySprite,x,y,300);

        FontRenderer fr=GuiHelper.getFontRenderer();
        int amount=stack.getAmount();
        String s;
        if(amount>=1000000)
            s=Integer.toString(amount/1000000)+"m";
        else if(amount>=1000)
            s=Integer.toString(amount/1000)+"k";
        else
            s=(stack.isEmpty()?TextFormatting.YELLOW:"")+Integer.toString(amount);
        fr.drawStringWithShadow(s, (float)(x + 19 - 2 - fr.getStringWidth(s)), (float)(y + 6 + 3), 16777215);

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
        GlStateManager.popAttrib();
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
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public boolean isVisible() {
        return entityStack!=null;
    }
}
