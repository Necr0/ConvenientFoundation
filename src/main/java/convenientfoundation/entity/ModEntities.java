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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModEntities {
    public static Biome[] zombie_biomes;

    static {
        List<Biome> biomes=ForgeRegistries.BIOMES.getEntries().stream()
                .filter(
                        (entry)->entry.getValue().getSpawnableList(EnumCreatureType.MONSTER).stream().anyMatch(
                                slEntry -> slEntry.entityClass==EntityZombie.class
                        )
                )
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        zombie_biomes = biomes.toArray(new Biome[biomes.size()]);
    }

    public static void init(){
        EntityRegistry.registerModEntity(LibEntities.specialItemRL, EntitySpecialItem.class, LibEntities.specialItemRL.toString(), LibEntities.specialItemEntityId, ConvenientFoundation.INSTANCE, 128, 5, true);
        EntityRegistry.registerModEntity(LibEntities.undeadMinerRL, EntityUndeadMiner.class, LibEntities.undeadMinerRL.toString(), LibEntities.undeadMinerEntityId, ConvenientFoundation.INSTANCE, 128, 5, true);
        EntityRegistry.registerEgg(LibEntities.undeadMinerRL,0x70786C,0x474E46);
        EntityRegistry.addSpawn(EntityUndeadMiner.class,250,1,2, EnumCreatureType.MONSTER,zombie_biomes);
    }
}
