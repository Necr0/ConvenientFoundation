package convenientfoundation.common.event;

import convenientfoundation.common.container.IContainerTickable;
import convenientfoundation.libs.LibMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ContainerTickHandler {
	
	@SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e)
    {
		if(e.player.openContainer instanceof IContainerTickable)
			((IContainerTickable)e.player.openContainer).tickContainer(e.player, e.side);
    }
}
