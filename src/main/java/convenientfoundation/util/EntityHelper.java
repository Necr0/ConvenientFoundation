package convenientfoundation.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class EntityHelper {

    public static boolean canEntitySeeSky(Entity e){
        return e.getEntityWorld().canBlockSeeSky(new BlockPos(e));
    }

    public static NBTTagCompound getPersistentTag(EntityPlayer p, String modid){
        NBTTagCompound tag = p.getEntityData();
        if(!tag.hasKey(p.PERSISTED_NBT_TAG))
            tag.setTag(p.PERSISTED_NBT_TAG,new NBTTagCompound());
        NBTTagCompound pers = tag.getCompoundTag(p.PERSISTED_NBT_TAG);
        if(!pers.hasKey(modid))
            pers.setTag(modid,new NBTTagCompound());
        return pers.getCompoundTag(modid);
    }

    public static boolean isEntityAirBorne(EntityLivingBase player){
        return (player.isAirBorne || (!player.onGround && !player.isInWater())) && !player.isElytraFlying();
    }

    public static <T extends Entity> List<T> getEntitiesInAABBStrict(World world, Class<? extends T> clazz, AxisAlignedBB aabb){
        return world.getEntitiesWithinAABB(clazz,aabb,(T t)->aabb.expand(0,.0000001d,0).contains(t.getPositionVector()));
    }
}
