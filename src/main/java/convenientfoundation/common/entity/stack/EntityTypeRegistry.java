package convenientfoundation.common.entity.stack;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.libs.LibMod;
import convenientfoundation.libs.LibRegistries;
import convenientfoundation.util.Helper;
import jline.internal.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Map;
import java.util.Optional;

/**
 * Created by Necro on 8/5/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class EntityTypeRegistry {

    @SideOnly(Side.CLIENT)
    public static TextureMap TEXTURE_MAP_ENTITY;

    public static IForgeRegistry<EntityType> ENTITY_REGISTRY;

    public static void init(){
        ConvenientFoundation.LOG.info("Initializing Entity Registry");
        new RegistryBuilder<EntityType>()
                .setMaxID(Short.MAX_VALUE)
                .setType(EntityType.class)
                .setName(LibRegistries.ENTITY_REGISTRY)
                .create();
        ENTITY_REGISTRY= GameRegistry.findRegistry(EntityType.class);
    }

    @SideOnly(Side.CLIENT)
    public static void initTextureMap(){
        TEXTURE_MAP_ENTITY = new TextureMap("textures/entity-type", textureMap -> {
            for(Map.Entry<ResourceLocation, EntityType> entry : EntityTypeRegistry.ENTITY_REGISTRY.getEntries()){
                ConvenientFoundation.LOG.info("Loading Entity Texture {} for {}",entry.getValue().getTexture(),entry.getKey());
                textureMap.registerSprite(entry.getValue().getTexture());
            }
        });
        Minecraft.getMinecraft().renderEngine.loadTickableTexture(LibRegistries.ENTITY_TEXTURE_LOCATION, TEXTURE_MAP_ENTITY);
    }

    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getEntitySprite(EntityType entity) {
        if(entity!=null){
            ResourceLocation entityRL = entity.getTexture();
            if (entityRL != null)
                return TEXTURE_MAP_ENTITY.getTextureExtry(entityRL.toString());
        }
        return TEXTURE_MAP_ENTITY.getMissingSprite();
    }

    @Nullable
    public static EntityType getEntityType(ResourceLocation entity){
        Optional<Map.Entry<ResourceLocation, EntityType>> typeEntry = ENTITY_REGISTRY.getEntries().stream().filter(entry -> entry.getValue().getEntityId().equals(entity)).findFirst();
        if(typeEntry.isPresent())
            return typeEntry.get().getValue();
        return null;
    }

    public static EntityType getEntityType(String entity){
        return getEntityType(new ResourceLocation(entity));
    }

    @Nullable
    public static EntityType getEntityType(Entity entity){
        return getEntityType(entity.getClass());
    }

    @Nullable
    public static EntityType getEntityType(Class<? extends Entity> entity){
        return getEntityType(EntityRegistry.getEntry(entity).getRegistryName());
    }
}
