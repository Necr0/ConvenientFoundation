package convenientfoundation.common.block.base;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.common.item.base.EnumItemCategory;
import convenientfoundation.common.item.base.StringHelper;
import convenientfoundation.libs.LibMod;
import ctg.BlockCTGTransparentA;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
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
public class CFBlockCTGTransparentA extends BlockCTGTransparentA {

    protected boolean defaultInfo=true;
    protected boolean defaultAdditionalInfo=false;
    protected boolean defaultJoke=false;
    protected boolean baublesRequiredInfo=false;
    protected EnumItemCategory category=null;

    public CFBlockCTGTransparentA(Material material) {
        super(material);
    }

    public CFBlockCTGTransparentA(String name, Material material) {
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

    public CFBlockCTGTransparentA setDefaultInfo(boolean defaultInfo) {
        this.defaultInfo = defaultInfo;
        return this;
    }

    public CFBlockCTGTransparentA setDefaultAdditionalInfo(boolean defaultAdditionalInfo) {
        this.defaultAdditionalInfo = defaultAdditionalInfo;
        return this;
    }

    public CFBlockCTGTransparentA setDefaultJoke(boolean defaultJoke) {
        this.defaultJoke = defaultJoke;
        return this;
    }

    public CFBlockCTGTransparentA setCategory(EnumItemCategory category) {
        this.category=category;
        return this;
    }

    public CFBlockCTGTransparentA setBaublesRequiredInfo(boolean baublesRequiredInfo) {
        this.baublesRequiredInfo = baublesRequiredInfo;
        return this;
    }

    public CFBlockCTGTransparentA setSoundType(SoundType soundType){
        super.setSoundType(soundType);
        return this;
    }
}
