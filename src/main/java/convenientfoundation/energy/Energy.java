package convenientfoundation.energy;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Created by Necro on 7/30/2017.
 */
public class Energy extends IForgeRegistryEntry.Impl<Energy>{

    public float efficiency;
    public float entropy;
    protected String unlocalizedName="energy.null.name";

    protected ResourceLocation texture;

    public Energy(ResourceLocation texture)
    {
        this(texture, 1.0f, 1.0f);
    }

    public Energy(ResourceLocation texture, float efficiency, float entropy)
    {
        this.texture = texture;
        this.efficiency = efficiency;
        this.entropy = entropy;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(){
        return texture;
    }

    public Energy setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    public Energy setUnlocalizedName(String domain,String name)
    {
        this.unlocalizedName = "energy."+domain+":"+name+".name";
        return this;
    }

    public Energy setUnlocalizedName(ResourceLocation rl)
    {
        this.unlocalizedName = "energy."+rl.getResourceDomain()+":"+rl.getResourcePath()+".name";
        return this;
    }

    public String getUnlocalizedName(EnergyStack stack){
        return unlocalizedName;
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName(@Nullable World world, EnergyStack stack){
        return I18n.format(getUnlocalizedName(stack));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ArrayList<String> list, World world, EnergyStack stack) {}


    /**
     * Returns the efficiency factor. Each unit of energy used will be able to do it's efficiency as relative measure of work.
     * @param world The world the EnergyStack is handled in.
     * @param stack The EnergyStack.
     * @return The Efficiency Factor.
     */
    public float getEfficiency(@Nullable World world, EnergyStack stack){
        return efficiency;
    }


    /**
     * Returns the entropy factor. Each unit of energy used will create it's entropy as excess heat. This is good for powered furnaces but bad for other types of machines.
     * @param world The world the EnergyStack is handled in.
     * @param stack The EnergyStack.
     * @return The Entropy Factor.
     */
    public float getEntropy(@Nullable World world, EnergyStack stack){
        return entropy;
    }
}
