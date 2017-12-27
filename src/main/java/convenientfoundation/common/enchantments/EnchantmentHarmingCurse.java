package convenientfoundation.common.enchantments;

import convenientfoundation.libs.LibEnchantments;
import convenientfoundation.libs.LibMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class EnchantmentHarmingCurse extends CFEnchantment {

    public static final EnchantmentHarmingCurse INSTANCE=new EnchantmentHarmingCurse();

    public EnchantmentHarmingCurse()
    {
        super(LibEnchantments.harming_curse, Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, EntityEquipmentSlot.MAINHAND);
        this.setCurse(true).setTreasure(true).setMaxLevel(3);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return enchantmentLevel*20;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return enchantmentLevel*20+20;
    }

    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event){
        if(event.getWorld().isRemote)
            return;
        EntityPlayer player=event.getPlayer();
        int level=EnchantmentHelper.getEnchantmentLevel(INSTANCE,player.getHeldItem(EnumHand.MAIN_HAND));
        if(level>0 && player.world.rand.nextInt(4)==0)
            player.attackEntityFrom(DamageSource.MAGIC,level*(0.45f+(player.world.rand.nextFloat()*0.45f)));
    }

}
