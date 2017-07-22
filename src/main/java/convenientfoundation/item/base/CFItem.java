package convenientfoundation.item.base;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.ModConstants;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Necro on 7/22/2017.
 */public class CFItem extends Item {

    protected boolean defaultInfo=true;
    protected boolean defaultAdditionalInfo=false;
    protected boolean defaultJoke=false;
    protected boolean baublesRequiredInfo=false;
    protected EnumItemCategory category=null;

    public CFItem(String name) {
        super();
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setCreativeTab(ConvenientFoundation.CREATIVETAB).setRegistryName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced){
        if(defaultJoke)
            tooltip.add(StringHelper.getJoke(stack));
        if(defaultInfo)
            tooltip.add(StringHelper.getInfo(stack));
        if(defaultAdditionalInfo)
            tooltip.add(StringHelper.getAdditionalInfo(stack));
        if(!Loader.isModLoaded("baubles")&&baublesRequiredInfo)
            tooltip.add(StringHelper.getBaublesRequiredInfo());
        if(category!=null)
            category.addTooltip(tooltip);
        super.addInformation(stack,player,tooltip,advanced);
    }

    public CFItem setDefaultInfo(boolean defaultInfo) {
        this.defaultInfo = defaultInfo;
        return this;
    }

    public CFItem setDefaultAdditionalInfo(boolean defaultAdditionalInfo) {
        this.defaultAdditionalInfo = defaultAdditionalInfo;
        return this;
    }

    public CFItem setDefaultJoke(boolean defaultJoke) {
        this.defaultJoke = defaultJoke;
        return this;
    }

    public CFItem setCategory(EnumItemCategory category) {
        this.category=category;
        return this;
    }

    public CFItem setBaublesRequiredInfo(boolean baublesRequiredInfo) {
        this.baublesRequiredInfo = baublesRequiredInfo;
        return this;
    }
}
