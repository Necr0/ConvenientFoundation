package convenientfoundation.capabilities.heat;

import net.minecraft.nbt.NBTTagDouble;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by Necro on 8/30/2017.
 */
public class HeatVessel implements IHeatVessel, INBTSerializable<NBTTagDouble> {
    public double heatLevel,criticalHeat,thermalConductivity;

    public HeatVessel(){this(0d, 1d, 0d);}

    public HeatVessel(double heatLevel, double thermalConductivity, double criticalHeat){
        this.heatLevel=heatLevel;
        this.thermalConductivity=thermalConductivity;
        this.criticalHeat=criticalHeat;
    }

    @Override
    public double getHeatLevel() {
        return heatLevel;
    }

    @Override
    public void setHeatLevel(double amount) {
        this.heatLevel=Math.max(amount,0d);
        onHeatChanged();
    }

    @Override
    public void increaseHeatLevel(double amount) {
        this.heatLevel=Math.max(this.heatLevel+amount,0d);
        onHeatChanged();
    }

    @Override
    public void decreaseHeatLevel(double amount) {
        this.heatLevel=Math.max(this.heatLevel-amount,0d);
        onHeatChanged();
    }

    @Override
    public boolean canOverheat() {
        return criticalHeat>0;
    }

    @Override
    public double getCriticalHeat() {
        return criticalHeat;
    }

    public void onHeatChanged(){
        if(canOverheat()&&heatLevel>=criticalHeat)
            onCriticalHeatReached();
    }

    public void onCriticalHeatReached(){}

    @Override
    public NBTTagDouble serializeNBT() {
        return new NBTTagDouble(heatLevel);
    }

    @Override
    public void deserializeNBT(NBTTagDouble nbt) {
        this.heatLevel=nbt.getDouble();
    }
}
