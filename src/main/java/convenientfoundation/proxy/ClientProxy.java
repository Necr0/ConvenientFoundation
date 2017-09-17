package convenientfoundation.proxy;

import convenientfoundation.energy.EnergyRegistry;
import convenientfoundation.capabilities.entity.EntityTypeRegistry;
import convenientfoundation.entity.undeadMiner.EntityUndeadMiner;
import convenientfoundation.entity.undeadMiner.RenderUndeadMiner;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;


public class ClientProxy extends CommonProxy {

    public Side getSide(){ return Side.CLIENT; }

    @Override
    public World getClientWorld(){ return FMLClientHandler.instance().getClient().world; }

    @Override
    public void registerRenderers(){
        RenderingRegistry.registerEntityRenderingHandler(
                EntityUndeadMiner.class,
                RenderUndeadMiner::new);
    }

    @Override
    public void registerReloadableResources(){
        EnergyRegistry.initTextureMap();
        EntityTypeRegistry.initTextureMap();
    }
}

