package convenientfoundation.common.capabilities.entity;

import convenientfoundation.common.entity.stack.EntityStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

/**
 * Created by Necro on 8/27/2017.
 */
public class EntityStorage implements IEntityStorageModifiable, INBTSerializable<NBTTagCompound> {
    private EntityStack entity;
    private int capacity;

    public EntityStorage(){this(255);}
    public EntityStorage(int capacity){this.capacity=capacity;}


    @Override
    public void setEntity(EntityStack stack) {
        this.entity=stack;
        this.entity.setAmount(Math.min(entity.getAmount(),capacity));
        onContentsChanged();
    }

    @Override
    public void setAmount(int amount) {
        this.entity.setAmount(Math.min(amount,capacity));
        onContentsChanged();
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity=capacity;
        this.entity.setAmount(Math.min(entity.getAmount(),capacity));
        onContentsChanged();
    }

    @Nullable
    @Override
    public EntityStack getEntity() {
        return entity;
    }

    @Override
    public int getAmount() {
        return entity.getAmount();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Nullable
    @Override
    public EntityStack insert(EntityStack entity, boolean simulate) {
        if(entity==null)
            return null;
        if(this.entity==null){
            EntityStack ret=entity.copy();
            int amount=ret.shrink(capacity);
            if(!simulate){
                this.entity=entity.copy();
                this.entity.setAmount(amount);
                onContentsChanged();
            }
            return ret;
        }else{
            if(EntityStack.canMerge(this.entity,entity)){
                EntityStack new_entity=EntityStack.merge(this.entity,entity);
                if(new_entity.getAmount()>capacity){
                    EntityStack ret=entity.copy();
                    ret.setAmount(new_entity.shrink(new_entity.getAmount()-capacity));
                    if(!simulate){
                        this.entity=new_entity;
                        onContentsChanged();
                    }
                    return ret;
                }else{
                    if(!simulate){
                        this.entity=new_entity;
                        onContentsChanged();
                    }
                    return null;
                }
            }else
                return entity;
        }
    }

    @Nullable
    @Override
    public EntityStack extract(EntityStack entity, boolean simulate) {
        if(this.entity==null)
            return null;
        return EntityStack.canMerge(this.entity,entity)?extract(entity.getAmount(),simulate):null;
    }

    @Nullable
    @Override
    public EntityStack extract(int amount, boolean simulate) {
        if(this.entity==null)
            return null;
        EntityStack new_entity=this.entity.copy();
        EntityStack ret=entity.copy();
        ret.setAmount(new_entity.shrink(amount));
        if(!simulate){
            this.entity=new_entity.isEmpty()?null:new_entity;
            onContentsChanged();
        }
        return ret;
    }

    public void onContentsChanged(){}

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt=new NBTTagCompound();
        if (this.entity != null)
        {
            nbt.setTag("ENTITY",this.entity.writeToNBT(new NBTTagCompound()));
        }
        nbt.setInteger("CAPACITY", this.getCapacity());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.setCapacity(nbt.getInteger("CAPACITY"));
        this.setEntity(EntityStack.readFromNBT(nbt.getCompoundTag("ENTITY")));
    }
}
