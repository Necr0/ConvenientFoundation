package convenientfoundation.capabilities.entity;

import convenientfoundation.libs.LibMod;
import net.minecraft.client.resources.I18n;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

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
    private EntityType entityType;
    public int amount;

    public EntityStack(Entity entity){
        this(entity,1);
    }

    public EntityStack(Entity entity,int amount){
        this.entityType=EntityTypeRegistry.getEntityType(entity);
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
        this.amount=amount;
    }

    public String getUnlocalizedName(){
        if(entityType!=null)
            return entityType.getUnlocalizedName(this);
        EntityEntry entry=ForgeRegistries.ENTITIES.getValue(new ResourceLocation(serializedEntity.getString("id")));
        if(entry!=null){
            return "entity."+entry.getName()+".name";
        }else{
            return "entity.null.name";
        }
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName(World w){
        if(entityType!=null)
            return entityType.getDisplayName(w,this);
        return getEntityWithPos(w,0d,0d,0d).getName();
    }

    @SideOnly(Side.CLIENT)
    public List<String> getTooltip(@Nullable World world){
        ArrayList<String> list = new ArrayList<>();
        list.add(this.getDisplayName(world));
        if(entityType!=null)
            return list;
        entityType.addInformation(list,world,this);
        return list;
    }

    public NBTTagCompound getRawNBT(){
        return serializedEntity;
    }

    public EntityType getType() {
        return entityType;
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

    //AMOUNT
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount=Math.max(amount,0);
    }

    public void grow(){
        this.amount++;
    }

    public void grow(int amount){
        this.amount+=amount;
    }

    public int shrink(){
        return this.shrink(1);
    }

    public int shrink(int amount){
        if(this.amount<amount){
            int ret=this.amount;
            this.amount=0;
            return ret;
        }
        this.amount-=amount;
        return amount;
    }

    //the stack should be replaced with null if it is empty
    public boolean isEmpty(){
        return this.amount<=0;
    }
}
