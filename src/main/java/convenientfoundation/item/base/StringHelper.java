package convenientfoundation.item.base;

import convenientfoundation.ModConstants;
import convenientfoundation.util.Helper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StringHelper {
    @SideOnly(Side.CLIENT)
    public static String getJoke(ItemStack stack){
        return TextFormatting.YELLOW+Helper.localize("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+".joke");
    }

    @SideOnly(Side.CLIENT)
    public static String getInfo(ItemStack stack){
        return Helper.localize("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+".info");
    }

    @SideOnly(Side.CLIENT)
    public static String getAdditionalInfo(ItemStack stack){
        return Helper.localize("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+".additionalInfo");
    }

    @SideOnly(Side.CLIENT)
    public static String getBaublesRequiredInfo(){
        return TextFormatting.RED+Helper.localize("tooltip." + ModConstants.Mod.MODID + ":baublesRequired");
    }

    @SideOnly(Side.CLIENT)
    public static String getHint(String loc, String... rep){
        return TextFormatting.DARK_GRAY+Helper.localize(loc,rep);
    }
}
