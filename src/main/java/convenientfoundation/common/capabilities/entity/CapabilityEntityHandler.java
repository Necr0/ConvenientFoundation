package convenientfoundation.common.capabilities.entity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Created by Necro on 8/27/2017.
 */
public class CapabilityEntityHandler {
    @CapabilityInject(IEntityHandler.class)
    public static Capability<IEntityHandler> ENTITY_HANDLER_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IEntityHandler.class, new DefaultEntityHandler<>(), EntityStorage::new);
    }

    private static class DefaultEntityHandler<T extends IEntityHandler> implements Capability.IStorage<T> {
        @Override
        public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side)
        {
            if (!(instance instanceof EntityStorage))
                throw new RuntimeException("Provided IEntityHandler is not instance of EntityStorage");
            EntityStorage storage = (EntityStorage) instance;
            return storage.serializeNBT();
        }

        @Override
        public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt)
        {
            if (!(instance instanceof EntityStorage))
                throw new RuntimeException("Provided IEntityHandler is not instance of EntityStorage");
            NBTTagCompound tags = (NBTTagCompound) nbt;
            EntityStorage storage = (EntityStorage) instance;
            storage.deserializeNBT(tags);
        }
    }
}
