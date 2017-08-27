package convenientfoundation.network;

import convenientfoundation.libs.LibMod;
import convenientfoundation.network.explosion.PacketExtendedExplosion;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(LibMod.MODID);

    public static void init() {
        int i = 0;
        INSTANCE.registerMessage(PacketExtendedExplosion.class, PacketExtendedExplosion.class, i++, Side.CLIENT);
        INSTANCE.registerMessage(PacketParticle.class, PacketParticle.class, i++, Side.CLIENT);
    }

    public static void spawnParticle(World w, EnumParticleTypes t, double x, double y, double z, double xs, double ys, double zs){
        INSTANCE.sendToAllAround(new PacketParticle(t,x,y,z,xs,ys,zs),new NetworkRegistry.TargetPoint(w.provider.getDimension(),x,y,z,64));
    }

    public static void spawnParticle(World w, EnumParticleTypes t, double x, double y, double z, double xs, double ys, double zs, int... params){
        INSTANCE.sendToAllAround(new PacketParticle(t,x,y,z,xs,ys,zs,params),new NetworkRegistry.TargetPoint(w.provider.getDimension(),x,y,z,64));
    }
}
