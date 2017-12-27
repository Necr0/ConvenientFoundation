package convenientfoundation.common.enchantments;

import convenientfoundation.CFConfig;
import convenientfoundation.libs.LibEnchantments;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.ExperienceHelper;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentEverlasting extends CFEnchantment {

    public static final EnchantmentEverlasting INSTANCE=new EnchantmentEverlasting();

    public EnchantmentEverlasting()
    {
        super(LibEnchantments.everlasting, Rarity.VERY_RARE, EnumEnchantmentType.ALL, EntityEquipmentSlot.values());
        this.setTreasure(true).setMaxLevel(1);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 25;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return 125;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EnchantmentVanishingCurse) && super.canApplyTogether(ench);
    }
}
