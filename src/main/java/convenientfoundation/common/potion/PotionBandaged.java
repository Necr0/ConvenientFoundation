package convenientfoundation.common.potion;

import convenientfoundation.libs.LibPotions;
import convenientfoundation.libs.LibImages;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 5/14/2017.
 */
public class PotionBandaged extends CFPotion {
    public static final PotionBandaged INSTANCE=new PotionBandaged();

    public PotionBandaged() {
        super(LibPotions.bandaged, false, 0xFFFFFF, LibImages.BANDAGED);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth())
        {
            entityLivingBaseIn.heal(0.3025f * (amplifier+1));
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        EntityLivingBase e=event.getEntityLiving();
        if(e.getEntityWorld().isRemote)
            return;
        PotionEffect effect=e.getActivePotionEffect(this);
        if(effect!=null){
            if(event.getAmount() >= 4f || e.getEntityWorld().rand.nextFloat() < event.getAmount()/4)
                e.removePotionEffect(this);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration%10==0;
    }
}
