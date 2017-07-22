package convenientfoundation.proxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;


public class ClientProxy extends CommonProxy {

    public Side getSide(){ return Side.CLIENT; }

    @Override
    public World getClientWorld(){ return FMLClientHandler.instance().getClient().world; }

    @Override
    public void registerRenderers(){}
}

