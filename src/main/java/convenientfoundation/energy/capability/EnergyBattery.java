package convenientfoundation.energy.capability;

import convenientfoundation.energy.EnergyStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

/**
 * Created by Necro on 8/27/2017.
 */
public class EnergyBattery implements IEnergyBatteryModifiable, INBTSerializable<NBTTagCompound> {
    private EnergyStack energy;
    private int capacity;

    public EnergyBattery(){this(255);}
    public EnergyBattery(int capacity){this.capacity=capacity;}


    @Override
    public void setEnergy(EnergyStack stack) {
        this.energy=stack;
        this.energy.setAmount(Math.min(energy.getAmount(),capacity));
        onContentsChanged();
    }

    @Override
    public void setEnergyAmount(int amount) {
        this.energy.setAmount(Math.min(amount,capacity));
        onContentsChanged();
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity=capacity;
        this.energy.setAmount(Math.min(energy.getAmount(),capacity));
        onContentsChanged();
    }

    @Nullable
    @Override
    public EnergyStack getEnergy() {
        return energy;
    }

    @Override
    public int getEnergyAmount() {
        return energy.getAmount();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Nullable
    @Override
    public EnergyStack insert(EnergyStack energy, boolean simulate) {
        if(energy==null)
            return null;
        if(this.energy==null){
            EnergyStack ret=energy.copy();
            int amount=ret.shrink(capacity);
            if(!simulate){
                this.energy=energy.copy();
                this.energy.setAmount(amount);
                onContentsChanged();
            }
            return ret;
        }else{
            if(EnergyStack.canMerge(this.energy,energy)){
                EnergyStack new_energy=EnergyStack.merge(this.energy,energy);
                if(new_energy.getAmount()>capacity){
                    EnergyStack ret=energy.copy();
                    ret.setAmount(new_energy.shrink(new_energy.getAmount()-capacity));
                    if(!simulate){
                        this.energy=new_energy;
                        onContentsChanged();
                    }
                    return ret;
                }else{
                    if(!simulate){
                        this.energy=new_energy;
                        onContentsChanged();
                    }
                    return null;
                }
            }else
                return energy;
        }
    }

    @Nullable
    @Override
    public EnergyStack extract(EnergyStack energy, boolean simulate) {
        if(this.energy==null)
            return null;
        return EnergyStack.canMerge(this.energy,energy)?extract(energy.getAmount(),simulate):null;
    }

    @Nullable
    @Override
    public EnergyStack extract(int amount, boolean simulate) {
        if(this.energy==null)
            return null;
        EnergyStack new_energy=this.energy.copy();
        EnergyStack ret=energy.copy();
        ret.setAmount(new_energy.shrink(amount));
        if(!simulate){
            this.energy=new_energy.isEmpty()?null:new_energy;
            onContentsChanged();
        }
        return ret;
    }

    public void onContentsChanged(){}

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt=new NBTTagCompound();
        if (this.energy != null)
        {
            nbt.setTag("ENERGY",this.energy.writeToNBT(new NBTTagCompound()));
        }
        nbt.setInteger("CAPACITY", this.getCapacity());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.setCapacity(nbt.getInteger("CAPACITY"));
        this.setEnergy(EnergyStack.readFromNBT(nbt.getCompoundTag("ENERGY")));
    }
}
