package convenientfoundation.entity;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.entity.specialitem.EntitySpecialItem;
import convenientfoundation.entity.undeadMiner.EntityUndeadMiner;
import convenientfoundation.libs.LibEntities;
import convenientfoundation.libs.LibMod;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Map;

public class ModEntities {
    public static Biome[] zombie_biomes;

    static {
        ArrayList<Biome> biomes=new ArrayList<>();
        for (Map.Entry<ResourceLocation, Biome> entry : ForgeRegistries.BIOMES.getEntries()) {
            for (Biome.SpawnListEntry spawnListEntry : entry.getValue().getSpawnableList(EnumCreatureType.MONSTER)) {
                if(spawnListEntry.entityClass==EntityZombie.class)
                    biomes.add(entry.getValue());
            }
        }
        zombie_biomes = biomes.toArray(new Biome[biomes.size()]);
    }

    public static void init(){
        EntityRegistry.registerModEntity(new ResourceLocation(LibMod.MODID, LibEntities.specialItemEntityName), EntitySpecialItem.class, LibEntities.specialItemEntityName, LibEntities.specialItemEntityId, ConvenientFoundation.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(new ResourceLocation(LibMod.MODID, LibEntities.undeadMinerEntityName), EntityUndeadMiner.class, LibEntities.undeadMinerEntityName, LibEntities.undeadMinerEntityId, ConvenientFoundation.INSTANCE, 128, 5, true);
        EntityRegistry.addSpawn(EntityUndeadMiner.class,250,1,2, EnumCreatureType.MONSTER,zombie_biomes);
    }
}
