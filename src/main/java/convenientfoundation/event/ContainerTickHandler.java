package convenientfoundation.event;

import convenientfoundation.container.IContainerTickable;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ContainerTickHandler {
	
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e)
    {
		if(e.player.openContainer instanceof IContainerTickable)
			((IContainerTickable)e.player.openContainer).tickContainer(e.player, e.side);
    }
}
