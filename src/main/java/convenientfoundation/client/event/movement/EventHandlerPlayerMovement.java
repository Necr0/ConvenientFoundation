package convenientfoundation.client.event.movement;

import convenientfoundation.libs.LibMod;
import convenientfoundation.util.Helper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MovementInput;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class EventHandlerPlayerMovement {
    public static boolean wasJumping=false;
    public static boolean wasSneaking=false;

    @SubscribeEvent
    public static void onClientUpdate(TickEvent.ClientTickEvent event){
        EntityPlayerSP player=Helper.getClientPlayer();
        if(player==null||event.phase!=TickEvent.Phase.START) return;

        MovementInput input=player.movementInput;
        if(!wasJumping && input.jump){
            MinecraftForge.EVENT_BUS.post(new PlayerMovementEvent.PlayerJumpEvent(player,player.getEntityWorld()));
        }
        if(!wasSneaking && input.sneak){
            MinecraftForge.EVENT_BUS.post(new PlayerMovementEvent.PlayerSneakEvent(player,player.getEntityWorld()));
        }

        wasJumping=input.jump;
        wasSneaking=input.sneak;
    }
}
