package convenientfoundation.config;

import convenientfoundation.libs.LibMod;
import net.minecraft.init.Blocks;

public class CFConfig {

    @Config(modid = LibMod.MODID, category = "geode")
    public static class Geode{
        @Config.Comment("Whether or not geodes should be dropped. This only enables/disables drops from ores, not undead miners, etc!")
        public static boolean enabled=true;

        @Config.Comment("a list mod ids for loot tables to be injected into the geode loot table, these will only be injected if the mod id is loaded")
        public static String[] mod_injects = {
                "thermalfoundation"
        };

        @Config.Comment("The ores that when mined drop geodes")
        public static String[] drop_ores = {
                Blocks.COAL_ORE.getRegistryName().toString(),
                Blocks.DIAMOND_ORE.getRegistryName().toString(),
                Blocks.LAPIS_ORE.getRegistryName().toString(),
                Blocks.EMERALD_ORE.getRegistryName().toString()
        };

        @Config.DecimalRange(min = 0f,max = 1f)
        public static float drop_chance=1/32f;
    }

    @Config(modid = LibMod.MODID, category = "shards")
    public static class Shards{
        @Config.Comment("Whether or not shards should be dropped. This only enables/disables drops from ores, not undead miners, etc!")
        public static boolean enabled=true;

        public static boolean diamond_shards=true;
        public static boolean emerald_shards=true;

        @Config.DecimalRange(min = 0f,max = 1f)
        public static float drop_chance=1/4f;
    }

    @Config(modid = LibMod.MODID, category = "arcane_dust")
    public static class ArcaneDust{
        @Config.Comment("Whether or not arcane dust should be dropped. This only enables/disables drops from ores, not witches, etc!")
        public static boolean enabled=true;

        public static String[] drop_ores = {
                Blocks.LAPIS_ORE.getRegistryName().toString(),
                Blocks.REDSTONE_ORE.getRegistryName().toString(),
                Blocks.LIT_REDSTONE_ORE.getRegistryName().toString()
        };

        @Config.DecimalRange(min = 0f,max = 1f)
        public static float drop_chance=1/4f;
    }
    
    @Config(modid = LibMod.MODID, category = "enchantments")
    public static class Enchantments{
        public static boolean enabled=true;
        public static boolean curses=true;
        public static boolean harming_curse=true;
        public static boolean draining_curse=true;
        @Config.Comment("excludes curses")
        public static boolean treasure_enchantments=true;
        public static boolean everlasting=true;
    }
}
