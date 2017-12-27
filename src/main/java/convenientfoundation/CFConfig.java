package convenientfoundation;

import convenientfoundation.libs.LibConfig;
import convenientfoundation.libs.LibMod;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Config;

import java.util.Arrays;
import java.util.List;

@Config(modid = LibMod.MODID,name = LibConfig.category_general)
public class CFConfig {

    public static boolean geodes=true;
    public static boolean shards=true;
    @Config.Comment("This only enables/disables drops from ores, not witches, etc!")
    public static boolean arcane_dust=true;


    public static boolean enchantments=true;

    @Config(modid = LibMod.MODID, category = "geode",name = LibConfig.category_geode)
    public static class Geode{
        @Config.Comment("0=disabled,1=automatic,2=enforced")
        @Config.RangeInt(min = 0, max = 2)
        public static int inject_thermalfoundation=1;

        @Config.Comment("The ores that when mined drop geodes")
        public static String[] drop_ores = {
                Blocks.COAL_ORE.getRegistryName().toString(),
                Blocks.DIAMOND_ORE.getRegistryName().toString(),
                Blocks.LAPIS_ORE.getRegistryName().toString(),
                Blocks.EMERALD_ORE.getRegistryName().toString()
        };

        @Config.RangeDouble(min = 0d,max = 1d)
        public static double drop_chance=1/32d;
    }

    @Config(modid = LibMod.MODID, category = "shards",name = LibConfig.category_shards)
    public static class Shards{
        public static boolean diamond_shards=true;
        public static boolean emerald_shards=true;

        @Config.RangeDouble(min = 0d,max = 1d)
        public static double drop_chance=1/4d;
    }

    @Config(modid = LibMod.MODID, category = "arcane_dust",name = LibConfig.category_arcane_dust)
    public static class ArcaneDust{
        public static String[] drop_ores = {
                Blocks.LAPIS_ORE.getRegistryName().toString(),
                Blocks.REDSTONE_ORE.getRegistryName().toString(),
                Blocks.LIT_REDSTONE_ORE.getRegistryName().toString()
        };

        @Config.RangeDouble(min = 0d,max = 1d)
        public static double drop_chance=1/4d;
    }

    @Config(modid = LibMod.MODID, category = "enchantments",name = LibConfig.category_enchantments)
    public static class Enchantments{
        public static boolean curses=true;
        public static boolean harming_curse=true;
        public static boolean draining_curse=true;
        @Config.Comment("excludes curses")
        public static boolean treasure_enchantments=true;
        public static boolean everlasting=true;
    }
}
