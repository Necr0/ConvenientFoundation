package convenientfoundation.util;

import convenientfoundation.gui.ImageResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHelper {
    @SideOnly(Side.CLIENT)
    public static RenderItem getRenderItem(){ return FMLClientHandler.instance().getClient().getRenderItem(); }

    @SideOnly(Side.CLIENT)
    public static FontRenderer getFontRenderer(){ return FMLClientHandler.instance().getClient().fontRenderer; }

    @SideOnly(Side.CLIENT)
    public static void playButtonPressSound(SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    @SideOnly(Side.CLIENT)
    public static void renderIRL(Minecraft mc, ImageResourceLocation irl, int x, int y){
        renderIRL(mc,irl,x,y,1.0F);
    }

    @SideOnly(Side.CLIENT)
    public static void renderIRL(Minecraft mc, ImageResourceLocation irl, int x, int y, float alpha){
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        mc.renderEngine.bindTexture(irl);
        if(mc.currentScreen!=null)
            mc.currentScreen.drawTexturedModalRect(x, y, irl.startX, irl.startY, irl.sizeX, irl.sizeY);
        else
            mc.ingameGUI.drawTexturedModalRect(x, y, irl.startX, irl.startY, irl.sizeX, irl.sizeY);
    }

/* The code below has been taken from mezz's JEI and modified.

The MIT License (MIT)

Copyright (c) 2014-2015 mezz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.*/
    @SideOnly(Side.CLIENT)
    public static void renderTAS(TextureAtlasSprite tas, int x, int y, int z){
        renderMaskedTAS(tas,x,y,z,0,0,0,0);
    }

    @SideOnly(Side.CLIENT)
    public static void renderMaskedTAS(TextureAtlasSprite tas, int x, int y,  int z, int maskLeft, int maskTop, int maskRight, int maskBottom){
        double uMin = (double) tas.getMinU();
        double uMax = (double) tas.getMaxU();
        double vMin = (double) tas.getMinV();
        double vMax = (double) tas.getMaxV();
        uMax = uMax - ((maskRight+maskLeft) / 16.0 * (uMax - uMin));
        vMax = vMax - ((maskTop+maskBottom) / 16.0 * (vMax - vMin));

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufBuild = tessellator.getBuffer();
        bufBuild.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufBuild.pos(x + maskLeft, y + 16 - maskBottom, z).tex(uMin, vMax).endVertex();
        bufBuild.pos(x + 16 - maskRight, y + 16 - maskBottom, z).tex(uMax, vMax).endVertex();
        bufBuild.pos(x + 16 - maskRight, y + maskTop, z).tex(uMax, vMin).endVertex();
        bufBuild.pos(x + maskLeft, y + maskTop, z).tex(uMin, vMin).endVertex();
        tessellator.draw();
    }
}
