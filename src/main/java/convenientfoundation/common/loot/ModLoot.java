package convenientfoundation.common.loot;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.config.CFConfig;
import convenientfoundation.libs.LibLoot;
import convenientfoundation.libs.LibMod;
import convenientfoundation.util.LootTableHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.*;
import java.util.*;

/**
 * Created by Necro on 9/12/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModLoot {
    //
    public static ResourceLocation inject_blaze;
    public static ResourceLocation inject_magma_cube;
    public static ResourceLocation inject_enderman;
    public static ResourceLocation inject_endermite;
    public static ResourceLocation inject_witch;
    //
    public static ResourceLocation geode;
    public static List<ResourceLocation> geode_mod_injects;
    //
    public static ResourceLocation undead_miner;
    public static ResourceLocation ender_dragon;

    public static void init(){
        LootConditionManager.registerCondition(new ConditionModLoaded.Serializer());
        inject_blaze=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_blaze));
        inject_magma_cube=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_magma_cube));
        inject_enderman=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_enderman));
        inject_endermite=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_endermite));
        inject_witch=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_witch));

        geode=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.geode));

        ConvenientFoundation.LOG.info("###Loading {} Geode Injects###",CFConfig.Geode.mod_injects.length);
        geode_mod_injects = new ArrayList<>();
        for (String modid:CFConfig.Geode.mod_injects) {
            if(Loader.isModLoaded(modid)){
                ResourceLocation loc=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.geode_base_path+modid));
                geode_mod_injects.add(loc);
                ConvenientFoundation.LOG.info("Loaded {} as {}",modid,loc.toString());
            }else{
                ConvenientFoundation.LOG.info("Skipping {}: Mod not loaded!",modid);
            }
        }
        ConvenientFoundation.LOG.info("###Loaded {} Geode Injects###",geode_mod_injects.size());

        undead_miner=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.undead_miner));
        ender_dragon=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.ender_dragon));
    }


    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent evt) {
        String name=evt.getName().toString();
        if (name.equals("minecraft:entities/blaze")) {
            LootTableHelper.addTableAsPool(evt.getTable(),inject_blaze,LibLoot.inject_blaze);
        }else if(name.equals("minecraft:entities/magma_cube")){
            LootTableHelper.addTableAsPool(evt.getTable(),inject_magma_cube,LibLoot.inject_magma_cube);
        }else if(name.equals("minecraft:entities/enderman")){
            LootTableHelper.addTableAsPool(evt.getTable(),inject_enderman,LibLoot.inject_enderman);
        }else if(name.equals("minecraft:entities/endermite")){
            LootTableHelper.addTableAsPool(evt.getTable(),inject_endermite,LibLoot.inject_endermite);
        }else if(name.equals("minecraft:entities/witch")){
            LootTableHelper.addTableAsPool(evt.getTable(),inject_witch,LibLoot.inject_witch);
        }else if(name.equals(LibMod.MODID+":"+LibLoot.geode)){
            ConvenientFoundation.LOG.info("###Injecting {} Geode Injects###",geode_mod_injects.size());
            LootTableManager manager = evt.getLootTableManager();
            LootTable geodeTable = evt.getTable();
            for (ResourceLocation r:geode_mod_injects) {
                LootTableHelper.mergeIntoTable(manager.getLootTableFromLocation(r), geodeTable);
                ConvenientFoundation.LOG.info("Injected {}.", r.toString());
            }
            ConvenientFoundation.LOG.info("###Finished Loading Geode Injects###");
        }
    }
}
