package convenientfoundation.util;

import convenientfoundation.ConvenientFoundation;
import convenientfoundation.libs.LibMappings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class LootTableHelper {
    public static final Field POOLS_FIELD = ReflectionHelper.findField(LootTable.class, LibMappings.Fields.LootTable_pools);
    public static final Field ENTRIES_FIELD = ReflectionHelper.findField(LootPool.class, LibMappings.Fields.LootPool_lootEntries);
    public static final Field ENTRYNAME_FIELD = ReflectionHelper.findField(LootEntry.class, LibMappings.Fields.LootEntry_entryName);

    public static LootTable mergeIntoTable(LootTable source, LootTable target){
        return mergeIntoTable(source,target,EnumDuplicateMode.REPLACE);
    }

    public static LootTable mergeIntoTable(LootTable sourceTable, LootTable targetTable, EnumDuplicateMode duplicateEntryMode){
        try {
            List<LootPool> sourcePools=(List<LootPool>)POOLS_FIELD.get(sourceTable);

            for(LootPool sourcePool:sourcePools){
                LootPool targetPool = targetTable.getPool(sourcePool.getName());

                if(targetPool != null){
                    mergeIntoPool(sourcePool,targetPool,duplicateEntryMode);
                }else
                    targetTable.addPool(sourcePool);
            }
        } catch (IllegalAccessException e) {
            ConvenientFoundation.LOG.error(e);
        }
        return targetTable;
    }

    public static LootPool mergeIntoPool(LootPool sourcePool, LootPool targetPool, EnumDuplicateMode duplicateEntryMode){
        try {
            List<LootEntry> sourceEntries=(List<LootEntry>)ENTRIES_FIELD.get(sourcePool);

            for(LootEntry entry:sourceEntries){
                String entryName=entry.getEntryName();
                LootEntry targetEntry = targetPool.getEntry(entryName);

                if(targetEntry != null) {

                    if (duplicateEntryMode==EnumDuplicateMode.SKIP)
                        continue;

                    if(duplicateEntryMode==EnumDuplicateMode.REPLACE){
                        targetPool.removeEntry(entryName);
                    }else{
                        ENTRYNAME_FIELD.set(entry, entryName + "#" + UUID.randomUUID().toString());
                    }
                    targetPool.addEntry(entry);

                }else
                    targetPool.addEntry(entry);

            }
        } catch (IllegalAccessException e) {
            ConvenientFoundation.LOG.error(e);
        }
        return targetPool;
    }

    public static LootPool addTableAsPool(LootTable table,ResourceLocation pool,String name){
        LootEntry entry = new LootEntryTable(pool, 1, 0, new LootCondition[0], name);
        LootPool lootPool = new LootPool(new LootEntry[]{entry}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0), name);
        table.addPool(lootPool);
        return lootPool;
    }

    enum EnumDuplicateMode{
        SKIP,
        REPLACE,
        RENAME
    }
}
