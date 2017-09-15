package convenientfoundation.event.resourceDrop;

import convenientfoundation.item.ModItems;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.Helper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 9/12/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class DragonScaleDropHandler {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event){
        EntityLivingBase e=event.getEntityLiving();
        World w=e.world;
        if(w.isRemote)
            return;

        if(event.getEntity() instanceof EntityDragon){
            int amount=4+w.rand.nextInt(4);
            for(int i=0;i<amount;i++){
                Helper.spawnItem(w,e.posX-1.5d+(w.rand.nextDouble()*3),e.posY-1.5d+(w.rand.nextDouble()*3),e.posZ-1.5d+(w.rand.nextDouble()*3),new ItemStack(ModItems.DRAGON_SCALE));
            }
        }
    }
}
