package convenientfoundation.potion;

import convenientfoundation.libs.LibPotions;
import convenientfoundation.libs.LibImages;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 5/14/2017.
 */
public class PotionThorns extends CFPotion {
    public static final PotionThorns INSTANCE=new PotionThorns();

    public PotionThorns() {
        super(LibPotions.thorns, false, 0x800000, LibImages.THORNS);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onDamageTaken(LivingAttackEvent event) {
        EntityLivingBase attacked=event.getEntityLiving();
        PotionEffect effect=attacked.getActivePotionEffect(this);
        DamageSource source=event.getSource();
        if(effect==null ||
                !(source.getTrueSource() instanceof EntityLivingBase) ||
                !(source instanceof EntityDamageSource) ||
                !(((EntityDamageSource)source).getIsThornsDamage()) ||
                (source.getImmediateSource()==null||source.getImmediateSource()==source.getTrueSource()) ||
                source.isDamageAbsolute() ||
                source.isMagicDamage() ||
                source.isFireDamage() ||
                source.isExplosion() ||
                source.isProjectile() ||
                source.isUnblockable())
            return;
        EntityLivingBase attacker=(EntityLivingBase) source.getTrueSource();
        attacker.attackEntityFrom(DamageSource.causeThornsDamage(attacked),event.getAmount()*0.2f*(effect.getAmplifier()+1));
    }
}
