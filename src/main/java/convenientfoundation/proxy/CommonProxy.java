package convenientfoundation.proxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public Side getSide() {
        return Side.SERVER;
    }

    public World getClientWorld() {
        return null;
    }

    public void registerRenderers() {}

    public void registerReloadableResources() {}
}
