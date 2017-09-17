package convenientfoundation.item.base;

import convenientfoundation.libs.LibMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StringHelper {
    @SideOnly(Side.CLIENT)
    public static String getJoke(ItemStack stack){
        return TextFormatting.YELLOW+I18n.format("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+".joke");
    }

    @SideOnly(Side.CLIENT)
    public static String getInfo(ItemStack stack){
        return I18n.format("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+".info");
    }

    @SideOnly(Side.CLIENT)
    public static String getAdditionalInfo(ItemStack stack){
        return I18n.format("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+".additional_info");
    }

    @SideOnly(Side.CLIENT)
    public static String getBaublesRequiredInfo(){
        return TextFormatting.RED+I18n.format("tooltip." + LibMod.MODID + ":baubles_required");
    }

    @SideOnly(Side.CLIENT)
    public static String getHint(String loc, String... rep){
        return TextFormatting.DARK_GRAY+I18n.format(loc,(Object[]) rep);
    }
}
