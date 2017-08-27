package convenientfoundation.capabilities.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Created by Necro on 7/27/2017.
 */
public class EntityStack {
    public static NBTTagList ZERO_LIST_D = new NBTTagList();
    public static NBTTagList ZERO_LIST_F = new NBTTagList();
    static {
        ZERO_LIST_D.set(0,new NBTTagDouble(0d));
        ZERO_LIST_D.set(1,new NBTTagDouble(0d));
        ZERO_LIST_D.set(2,new NBTTagDouble(0d));
        ZERO_LIST_F.set(0,new NBTTagFloat(0f));
        ZERO_LIST_F.set(1,new NBTTagFloat(0f));
    }

    private NBTTagCompound serializedEntity;
    private boolean isLiving;
    public int amount;

    public EntityStack(Entity entity){
        if(entity instanceof EntityLivingBase)
            isLiving=true;
        NBTTagCompound tag=entity.serializeNBT();
        tag.setTag("Pos", ZERO_LIST_D.copy());
        tag.setTag("Motion", ZERO_LIST_D.copy());
        tag.setTag("Rotation", ZERO_LIST_F.copy());

        tag.removeTag("UUIDMost");
        tag.removeTag("UUIDLeast");
        tag.setFloat("FallDistance",0f);
        tag.setShort("Fire",(short) 0);
        tag.setShort("Air",(short) 0);
        tag.setBoolean("OnGround", false);
        tag.removeTag("Dimension");
        tag.setInteger("PortalCooldown",0);

        serializedEntity=tag;
    }

    public String getUnlocalizedName(){
        EntityEntry entry=ForgeRegistries.ENTITIES.getValue(new ResourceLocation(serializedEntity.getString("id")));
        if(entry!=null){
            return "entity."+entry.getName()+".name";
        }else{
            return "entity.null.name";
        }
    }

    public String getDisplayName(World w){
        return getEntityWithPos(w,0d,0d,0d).getName();
    }

    public NBTTagCompound getRawNBT(){
        return serializedEntity;
    }

    public boolean isLiving(){
        return isLiving;
    }

    public Entity getEntityWithPos(World w,double x,double y,double z){
        return AnvilChunkLoader.readWorldEntityPos(serializedEntity,w,x,y,z,false);
    }

    public Entity spawnEntityAt(World w,double x,double y,double z){
        return AnvilChunkLoader.readWorldEntityPos(serializedEntity,w,x,y,z,true);
    }
}
