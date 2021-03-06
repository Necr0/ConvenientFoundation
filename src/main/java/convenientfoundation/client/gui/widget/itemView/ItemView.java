package convenientfoundation.client.gui.widget.itemView;

import convenientfoundation.client.gui.widget.IWidgetDrawable;
import convenientfoundation.client.gui.widget.IWidgetTooltip;
import convenientfoundation.util.ClientHelper;
import convenientfoundation.util.GuiHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemView implements IWidgetDrawable, IWidgetTooltip {

    public ItemStack itemStack;
    int posX,posY;

    public ItemView(@Nonnull ItemStack itemStack, int posX, int posY){
        this.itemStack=itemStack;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public boolean hasTooltip(GuiScreen guiScreen) {
        return !itemStack.isEmpty();
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        return itemStack.getTooltip(ClientHelper.getPlayer(), ITooltipFlag.TooltipFlags.NORMAL);
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return true;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        drawItem(itemStack);
    }

    public void drawItem(ItemStack stack){
        RenderItem itemRender=GuiHelper.getRenderItem();
        itemRender.zLevel = 100.0F;

        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableDepth();
        itemRender.renderItemAndEffectIntoGUI(ClientHelper.getPlayer(), stack, posX, posY);
        itemRender.renderItemOverlayIntoGUI(GuiHelper.getFontRenderer(), stack, posX, posY, null);

        itemRender.zLevel = 0.0F;
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
        return true;
    }
}
