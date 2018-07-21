package convenientfoundation.common.block.base;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.common.item.base.EnumItemCategory;
import convenientfoundation.common.item.base.StringHelper;
import convenientfoundation.libs.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Necro on 7/22/2017.
 */
public class CFBlock extends Block {

    protected boolean defaultInfo=true;
    protected boolean defaultAdditionalInfo=false;
    protected boolean defaultJoke=false;
    protected boolean baublesRequiredInfo=false;
    protected EnumItemCategory category=null;

    public CFBlock(String name, Material material) {
        super(material);
        this.setTranslationKey(LibMod.MODID + ":" + name).setCreativeTab(ConvenientFoundation.CREATIVETAB).setRegistryName(name);
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

    public CFBlock setDefaultInfo(boolean defaultInfo) {
        this.defaultInfo = defaultInfo;
        return this;
    }

    public CFBlock setDefaultAdditionalInfo(boolean defaultAdditionalInfo) {
        this.defaultAdditionalInfo = defaultAdditionalInfo;
        return this;
    }

    public CFBlock setDefaultJoke(boolean defaultJoke) {
        this.defaultJoke = defaultJoke;
        return this;
    }

    public CFBlock setCategory(EnumItemCategory category) {
        this.category=category;
        return this;
    }

    public CFBlock setBaublesRequiredInfo(boolean baublesRequiredInfo) {
        this.baublesRequiredInfo = baublesRequiredInfo;
        return this;
    }
}
