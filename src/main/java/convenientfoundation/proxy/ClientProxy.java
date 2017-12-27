package convenientfoundation.proxy;

import convenientfoundation.common.energy.EnergyRegistry;
import convenientfoundation.common.entity.stack.EntityTypeRegistry;
import convenientfoundation.common.entity.undeadMiner.EntityUndeadMiner;
import convenientfoundation.common.entity.undeadMiner.RenderUndeadMiner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
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

