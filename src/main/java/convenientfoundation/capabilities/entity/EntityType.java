package convenientfoundation.capabilities.entity;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Created by Necro on 9/17/2017.
 */
public class EntityType extends IForgeRegistryEntry.Impl<EntityType>{
    protected String unlocalizedName="entity.null.name";

    protected Class<? extends Entity> entityClass;

    protected ResourceLocation texture;

    public EntityType(Class<? extends Entity> entityClass, ResourceLocation texture) {
        this.entityClass = entityClass;
        this.texture = texture;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(){
        return texture;
    }

    public EntityType setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    public EntityType setUnlocalizedName(String domain,String name)
    {
        this.unlocalizedName = "entity."+domain+":"+name+".name";
        return this;
    }

    public EntityType setUnlocalizedName(ResourceLocation rl)
    {
        this.unlocalizedName = "entity."+rl.getResourceDomain()+":"+rl.getResourcePath()+".name";
        return this;
    }

    public String getUnlocalizedName(EntityStack stack){
        return unlocalizedName;
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName(@Nullable World world, EntityStack stack){
        return I18n.format(getUnlocalizedName(stack));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ArrayList<String> list, World world, EntityStack stack) {}

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
    public ResourceLocation getEntityId() {
        return EntityRegistry.getEntry(entityClass).getRegistryName();
    }
}
