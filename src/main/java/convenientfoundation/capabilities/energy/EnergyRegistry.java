package convenientfoundation.capabilities.energy;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.libs.LibRegistries;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Map;

/**
 * Created by Necro on 8/5/2017.
 */
public class EnergyRegistry implements IResourceManagerReloadListener{

    protected static TextureMap TEXTURE_MAP_ENERGY;

    public static IForgeRegistry<Energy> ENERGY_REGISTRY;

    public static void init(){
        ConvenientFoundation.LOG.info("Initializing Energy Registry");
        new RegistryBuilder<Energy>()
                .setMaxID(Short.MAX_VALUE)
                .setType(Energy.class)
                .setName(LibRegistries.ENERGY_REGISTRY)
                .create();
        TEXTURE_MAP_ENERGY = new TextureMap("textures/energy");
        ENERGY_REGISTRY=GameRegistry.findRegistry(Energy.class);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        ConvenientFoundation.LOG.info("START Loading Energy Textures");
        TEXTURE_MAP_ENERGY.loadSprites(resourceManager, textureMap -> {
            for(Map.Entry<ResourceLocation, Energy> entry : EnergyRegistry.ENERGY_REGISTRY.getEntries()){
                ConvenientFoundation.LOG.info("Loading Energy Texture {} for {}",entry.getValue().getTexture(),entry.getKey());
                textureMap.registerSprite(entry.getValue().getTexture());
            }
        });
        ConvenientFoundation.LOG.info("FINISH Loading Energy Textures");
        //mc.renderEngine.loadTickableTexture(TextureMap.LOCATION_BLOCKS_TEXTURE, TEXTURE_MAP_ENERGY);
        //renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    }

    public static Energy getEnergy(ResourceLocation energy){
        return ENERGY_REGISTRY.getValue(energy);
    }

    public static Energy getEnergy(String energy){
        return ENERGY_REGISTRY.getValue(new ResourceLocation(energy));
    }
}
