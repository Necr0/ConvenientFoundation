package convenientfoundation.common.energy;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.common.entity.stack.EntityTypeRegistry;
import convenientfoundation.libs.LibMod;
import convenientfoundation.libs.LibRegistries;
import convenientfoundation.util.Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockFluidRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Map;

/**
 * Created by Necro on 8/5/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class EnergyRegistry{

    @SideOnly(Side.CLIENT)
    public static TextureMap TEXTURE_MAP_ENERGY;

    public static IForgeRegistry<Energy> ENERGY_REGISTRY;

    public static void init(){
        ConvenientFoundation.LOG.info("Initializing Energy Registry");
        new RegistryBuilder<Energy>()
                .setMaxID(Short.MAX_VALUE)
                .setType(Energy.class)
                .setName(LibRegistries.ENERGY_REGISTRY)
                .create();
        ENERGY_REGISTRY= GameRegistry.findRegistry(Energy.class);
    }

    @SideOnly(Side.CLIENT)
    public static void initTextureMap(){
        TEXTURE_MAP_ENERGY = new TextureMap("textures/energy", textureMap -> {
            for(Map.Entry<ResourceLocation, Energy> entry : EnergyRegistry.ENERGY_REGISTRY.getEntries()){
                ConvenientFoundation.LOG.info("Loading Energy Texture {} for {}",entry.getValue().getTexture(),entry.getKey());
                textureMap.registerSprite(entry.getValue().getTexture());
            }
        });
        Minecraft.getMinecraft().renderEngine.loadTickableTexture(LibRegistries.ENERGY_TEXTURE_LOCATION, TEXTURE_MAP_ENERGY);
    }

    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getEnergySprite(Energy energy) {
        ResourceLocation energyRL = energy.getTexture();
        if (energyRL != null)
            return TEXTURE_MAP_ENERGY.getTextureExtry(energyRL.toString());
        else
            return TEXTURE_MAP_ENERGY.getMissingSprite();
    }

    public static Energy getEnergy(ResourceLocation energy){
        return ENERGY_REGISTRY.getValue(energy);
    }

    public static Energy getEnergy(String energy){
        return ENERGY_REGISTRY.getValue(new ResourceLocation(energy));
    }
}
