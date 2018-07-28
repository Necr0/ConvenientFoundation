package convenientfoundation.common.enchantments;

import convenientfoundation.libs.LibEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentVanishingCurse;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

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
