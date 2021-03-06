package convenientfoundation.common.potion;

import convenientfoundation.libs.LibImages;
import convenientfoundation.libs.LibPotions;
import convenientfoundation.util.BlockHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 5/14/2017.
 */
public class PotionLumbering extends CFPotion {
    public static final PotionLumbering INSTANCE=new PotionLumbering();

    public PotionLumbering() {
        super(LibPotions.lumbering, false, 0xFFD400, LibImages.LUMBERING);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        PotionEffect active=player.getActivePotionEffect(PotionLumbering.INSTANCE);
        if(active!=null&&BlockHelper.doesOreDictMatch(event.getState(),"logWood",false)){
            event.setNewSpeed(event.getNewSpeed()*(1<<(active.getAmplifier()+1)));
        }
    }
}
