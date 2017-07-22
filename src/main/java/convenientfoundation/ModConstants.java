package convenientfoundation;

public class ModConstants {
    public static class Mod {
        public static final String MODNAME = "Convenient Foundation";
        public static final String MODID = "convfound";
        public static final String BUILD = "GRADLE:BUILD";
        public static final String VERSION = "GRADLE:VERSION-" + BUILD;
        public static final String DEPENDENCIES = "required-after:forge@[14.21.1.2415 ,);after:baubles";
        public static final String commonProxyClassPath = "convenientfoundation.proxy.CommonProxy";
        public static final String clientProxyClassPath = "convenientfoundation.proxy.ClientProxy";
    }

    public static class ItemNames {
        public static final String slime_bucket = "slime_bucket";
        public static final String wrench = "wrench";
    }

    public static class BlockNames {
    }

    public static class PotionNames {
        public static final String lumbering="lumbering";
        public static final String thorns="thorns";
        public static final String bandaged="bandaged";
    }

    public static class Entities {
        public static final int specialItemEntityId = 0;
        public static final String specialItemEntityName = "special_item";
    }

    public static class Compat {
    }

    public static class Items {
    }

    public static class Dimensions {
    }
}
