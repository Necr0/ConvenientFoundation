package convenientfoundation.common.enchantments;

import convenientfoundation.libs.LibMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class CFEnchantment extends Enchantment {
    private boolean curse;
    private boolean treasure;
    private int maxLevel;


    public CFEnchantment(String name, Enchantment.Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot... slots)
    {
        super(rarity, type, slots);
        this.setName(LibMod.MODID+":"+name).setRegistryName(new ResourceLocation(LibMod.MODID, name));
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return maxLevel;
    }

    public boolean isTreasureEnchantment()
    {
        return treasure;
    }

    public boolean isCurse()
    {
        return curse;
    }

    public CFEnchantment setCurse(boolean curse) {
        this.curse = curse;
        return this;
    }

    public CFEnchantment setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }

    public CFEnchantment setTreasure(boolean treasure) {
        this.treasure = treasure;
        return this;
    }
}
