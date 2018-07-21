package convenientfoundation.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientHelper {

    public static EntityPlayerSP getPlayer(){
        return FMLClientHandler.instance().getClient().player;
    }

    public static WorldClient getWorld(){
        return FMLClientHandler.instance().getClient().world;
    }

    public static Minecraft getMinecraft(){
        return FMLClientHandler.instance().getClient();
    }

    public static void displayGui(GuiScreen guiScreen){
        getMinecraft().displayGuiScreen(guiScreen);
    }
}
