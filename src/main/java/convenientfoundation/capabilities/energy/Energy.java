package convenientfoundation.capabilities.energy;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

/**
 * Created by Necro on 7/30/2017.
 */
public class Energy extends IForgeRegistryEntry.Impl<Energy>{

    public float entropy;
    protected String unlocalizedName;

    protected ResourceLocation texture;

    public Energy(ResourceLocation texture)
    {
        this(texture, 1.0f);
    }

    public Energy(ResourceLocation texture, float entropy)
    {
        this.texture = texture;
        this.entropy = entropy;
    }

    public ResourceLocation getTexture(){
        return texture;
    }

    public Energy setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    public String getUnlocalizedName(EnergyStack stack){
        return unlocalizedName;
    }

    public String getDisplayName(World world, EnergyStack stack){
        return I18n.format(getUnlocalizedName(stack));
    }


    /**
     * Returns the entropy factor. Each unit of energy used will create it's entropy as excess heat. This is good for powered furnaces but bad for other types of machines.
     * @param world The world the EnergyStack is handled in.
     * @param entity The entity carrying/handling/interacting with the EnergyStack.
     * @param pos The position of the machine/block handling the EnergyStack.
     * @param stack The EnergyStack.
     * @return The Entropy Factor.
     */
    public float getEntropy(@Nullable World world, @Nullable Entity entity, @Nullable BlockPos pos, EnergyStack stack){
        return entropy;
    }
}
