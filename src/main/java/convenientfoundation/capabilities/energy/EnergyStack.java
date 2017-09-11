package convenientfoundation.capabilities.energy;

import convenientfoundation.libs.LibMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Necro on 7/30/2017.
 */
public class EnergyStack{

    private Energy type;
    private int amount;
    private NBTTagCompound tag;

    public EnergyStack(@Nonnull Energy type, int amount){
        this.type=type;
        this.amount=amount;
    }

    public Energy getType() {
        return type;
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

    @SideOnly(Side.CLIENT)
    public List<String> getTooltip(@Nullable World world, int capacity){
        ArrayList<String> list = new ArrayList<>();
        list.add(this.getDisplayName(world));
        type.addInformation(list,world,this);
        list.add(I18n.format("tooltip."+ LibMod.MODID +":energy.amount/capacity",this.getAmount(),capacity));
        list.add(I18n.format("tooltip."+ LibMod.MODID +":energy.efficiency",this.getEfficiency(world)));
        list.add(I18n.format("tooltip."+ LibMod.MODID +":energy.entropy",this.getEntropy(world)));
        return list;
    }

    @SideOnly(Side.CLIENT)
    public List<String> getTooltip(@Nullable World world){
        ArrayList<String> list = new ArrayList<>();
        list.add(this.getDisplayName(world));
        type.addInformation(list,world,this);
        list.add(I18n.format("tooltip."+ LibMod.MODID +":energy.amount",this.getAmount()));
        list.add(I18n.format("tooltip."+ LibMod.MODID +":energy.efficiency",this.getEfficiency(world)));
        list.add(I18n.format("tooltip."+ LibMod.MODID +":energy.entropy",this.getEntropy(world)));
        return list;
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName(@Nullable World world){
        return type.getDisplayName(world,this);
    }

    public float getEfficiency(@Nullable World world){
        return type.getEfficiency(world,this);
    }

    public float getEntropy(@Nullable World world){
        return type.getEntropy(world,this);
    }

    //the stack should be replaced with null if it is empty
    public boolean isEmpty(){
        return this.amount<=0;
    }

    //MERGING
    public static boolean canMerge(EnergyStack energyStack1,EnergyStack energyStack2){
        return (energyStack1.getType()==energyStack2.getType()&&energyStack1.getTagCompound().equals(energyStack2.getTagCompound()));
    }

    public static EnergyStack merge(EnergyStack energyStack1,EnergyStack energyStack2){
        EnergyStack ret=energyStack1.copy();
        ret.grow(energyStack2.getAmount());
        return ret;
    }

    //TAG
    public boolean hasTagCompound(){
        return tag!=null;
    }

    @Nullable
    public NBTTagCompound getTagCompound(){
        return tag;
    }

    public void setTagCompound(NBTTagCompound tag){
        this.tag=tag;
    }

    //SERIALIZE
    public NBTTagCompound writeToNBT(NBTTagCompound nbt){
        nbt.setString("TYPE",type.getRegistryName().toString());
        nbt.setInteger("AMOUNT",amount);
        if(hasTagCompound())
            nbt.setTag("TAG",getTagCompound().copy());
        return nbt;
    }

    @Nullable
    public static EnergyStack readFromNBT(NBTTagCompound nbt){
        EnergyStack ret=null;
        if(nbt.hasKey("TYPE", Constants.NBT.TAG_STRING) && nbt.hasKey("AMOUNT", Constants.NBT.TAG_INT)){
            ret=new EnergyStack(EnergyRegistry.getEnergy(nbt.getString("TYPE")),nbt.getInteger("AMOUNT"));
            if(nbt.hasKey("TAG", Constants.NBT.TAG_COMPOUND))
                ret.setTagCompound(nbt.getCompoundTag("TAG").copy());
        }
        return ret;
    }

    public EnergyStack copy(){
        EnergyStack ret=new EnergyStack(getType(),getAmount());
        ret.setTagCompound(getTagCompound().copy());
        return ret;
    }
}
