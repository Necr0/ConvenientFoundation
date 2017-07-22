package convenientfoundation.event;

import convenientfoundation.event.inventoryTick.PlayerInventoryTickHandler;
import convenientfoundation.event.movement.EventHandlerPlayerMovement;
import convenientfoundation.event.soulbound.EventHandlerSoulbound;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Necro on 7/22/2017.
 */
public class ModEventHandlers {
    public static void init(){
        MinecraftForge.EVENT_BUS.register(new PlayerInventoryTickHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlerSoulbound());
        MinecraftForge.EVENT_BUS.register(new ContainerTickHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandlerPlayerMovement());
    }
}
