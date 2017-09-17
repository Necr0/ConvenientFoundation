package convenientfoundation.capabilities.entity;

import convenientfoundation.entity.undeadMiner.EntityUndeadMiner;
import convenientfoundation.libs.LibEntities;
import convenientfoundation.libs.LibMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by Necro on 8/5/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModEntityTypes {
    public static EntityType UNDEAD_MINER = new EntityType(EntityUndeadMiner.class,LibEntities.undeadMinerRL).setUnlocalizedName(LibEntities.undeadMinerRL).setRegistryName(LibEntities.undeadMinerRL);

    @SubscribeEvent
    public static void registerEntityTypes(RegistryEvent.Register<EntityType> evt) {
        IForgeRegistry<EntityType> r = evt.getRegistry();

        registerVanillaEntityTypes(r);
        r.register(UNDEAD_MINER);
    }

    private static void registerVanillaEntityTypes(IForgeRegistry<EntityType> r){
        r.register(generateVanillaEntityType(EntityBlaze.class,"blaze","Blaze"));
        r.register(generateVanillaEntityType(EntityCaveSpider.class,"cave_spider","CaveSpider"));
        r.register(generateVanillaEntityType(EntityChicken.class,"chicken","Chicken"));
        r.register(generateVanillaEntityType(EntityCow.class,"cow","Cow"));
        r.register(generateVanillaEntityType(EntityCreeper.class,"creeper","Creeper"));
        r.register(generateVanillaEntityType(EntityEnderman.class,"enderman","Enderman"));
        r.register(generateVanillaEntityType(EntityGhast.class,"ghast","Ghast"));
        r.register(generateVanillaEntityType(EntityHusk.class,"husk","Husk"));
        r.register(generateVanillaEntityType(EntityMooshroom.class,"mooshroom","MushroomCow"));
        r.register(generateVanillaEntityType(EntityPig.class,"pig","Pig"));
        r.register(generateVanillaEntityType(EntityPolarBear.class,"polar_bear","PolarBear"));
        r.register(generateVanillaEntityType(EntitySheep.class,"sheep","Sheep"));
        r.register(generateVanillaEntityType(EntitySkeleton.class,"skeleton","Skeleton"));
        r.register(generateVanillaEntityType(EntitySpider.class,"spider","Spider"));
        r.register(generateVanillaEntityType(EntityStray.class,"stray","Stray"));
        r.register(generateVanillaEntityType(EntityVillager.class,"villager","Villager"));
        r.register(generateVanillaEntityType(EntityWitherSkeleton.class,"wither_skeleton","WitherSkeleton"));
        r.register(generateVanillaEntityType(EntityZombie.class,"zombie","Zombie"));
        r.register(generateVanillaEntityType(EntityPigZombie.class,"zombie_pigman","PigZombie"));
        r.register(generateVanillaEntityType(EntityZombieVillager.class,"zombie_villager","ZombieVillager"));
    }

    private static EntityType generateVanillaEntityType(Class<? extends Entity> c,String id, String name){
        ResourceLocation rl1=new ResourceLocation(LibMod.MODID,"vanilla/"+id);
        ResourceLocation rl2=new ResourceLocation(LibMod.MODID,id);
        return new EntityType(c,rl1).setUnlocalizedName("entity."+name+".name").setRegistryName(rl2);
    }
}
