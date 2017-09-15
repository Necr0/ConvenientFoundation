package convenientfoundation.loot;

import convenientfoundation.libs.LibLoot;
import convenientfoundation.libs.LibMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Necro on 9/12/2017.
 */
@Mod.EventBusSubscriber(modid = LibMod.MODID)
public class ModLoot {
    public static ResourceLocation inject_blaze;
    public static ResourceLocation inject_magma_cube;
    public static ResourceLocation inject_enderman;
    public static ResourceLocation inject_endermite;
    public static ResourceLocation inject_witch;
    //
    public static ResourceLocation geode;
    //
    public static ResourceLocation undead_miner;

    public static void init(){
        inject_blaze=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_blaze));
        inject_magma_cube=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_magma_cube));
        inject_enderman=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_enderman));
        inject_endermite=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_endermite));
        inject_witch=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.inject_witch));
        geode=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.geode));
        undead_miner=LootTableList.register(new ResourceLocation(LibMod.MODID, LibLoot.undead_miner));
    }


    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent evt) {
        if (evt.getName().toString().equals("minecraft:entities/blaze")) {
            injectPool(evt.getTable(),inject_blaze,LibLoot.inject_blaze);
        }else if(evt.getName().toString().equals("minecraft:entities/magma_cube")){
            injectPool(evt.getTable(),inject_magma_cube,LibLoot.inject_magma_cube);
        }else if(evt.getName().toString().equals("minecraft:entities/enderman")){
            injectPool(evt.getTable(),inject_enderman,LibLoot.inject_enderman);
        }else if(evt.getName().toString().equals("minecraft:entities/endermite")){
            injectPool(evt.getTable(),inject_endermite,LibLoot.inject_endermite);
        }else if(evt.getName().toString().equals("minecraft:entities/witch")){
            injectPool(evt.getTable(),inject_witch,LibLoot.inject_witch);
        }
    }

    public static void injectPool(LootTable table,ResourceLocation pool,String name){
        LootEntry entry = new LootEntryTable(pool, 1, 0, new LootCondition[0], name);
        LootPool lootPool = new LootPool(new LootEntry[]{entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0), name);
        table.addPool(lootPool);
    }
}
