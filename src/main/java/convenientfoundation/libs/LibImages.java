package convenientfoundation.libs;

import convenientfoundation.client.gui.ImageResourceLocation;

public class LibImages {
    public static final ImageResourceLocation DEMO_BACKGROUND = new ImageResourceLocation("minecraft","textures/gui/demo_background.png", 0, 0, 248, 166);
    public static final ImageResourceLocation DEMO_BACKGROUND_WIDE = new ImageResourceLocation(LibMod.MODID,"textures/gui/demo_background_wide.png", 0, 0, 256, 80);

    private static final String COMMON_ATLAS= LibMod.MODID + ":textures/gui/common_atlas.png";
    public static final ImageResourceLocation RESET = new ImageResourceLocation(COMMON_ATLAS, 16, 0, 16, 16);
    public static final ImageResourceLocation DV = new ImageResourceLocation(COMMON_ATLAS, 0, 32, 16, 16);
    public static final ImageResourceLocation NODV = new ImageResourceLocation(COMMON_ATLAS, 16, 32, 16, 16);
    public static final ImageResourceLocation NBT = new ImageResourceLocation(COMMON_ATLAS, 0, 16, 16, 16);
    public static final ImageResourceLocation NONBT = new ImageResourceLocation(COMMON_ATLAS, 16, 16, 16, 16);
    public static final ImageResourceLocation FILTER = new ImageResourceLocation(COMMON_ATLAS, 0, 96, 16, 16);
    public static final ImageResourceLocation NOFILTER = new ImageResourceLocation(COMMON_ATLAS, 16, 96, 16, 16);
    public static final ImageResourceLocation BLACKLIST = new ImageResourceLocation(COMMON_ATLAS, 32, 96, 16, 16);
    public static final ImageResourceLocation WHITELIST = new ImageResourceLocation(COMMON_ATLAS, 48, 96, 16, 16);
    public static final ImageResourceLocation HIGHRS = new ImageResourceLocation(COMMON_ATLAS, 0, 48, 16, 16);
    public static final ImageResourceLocation NORS = new ImageResourceLocation(COMMON_ATLAS, 16, 48, 16, 16);
    public static final ImageResourceLocation LOWRS = new ImageResourceLocation(COMMON_ATLAS, 32, 48, 16, 16);
    public static final ImageResourceLocation PULSERS = new ImageResourceLocation(COMMON_ATLAS, 48, 48, 16, 16);
    public static final ImageResourceLocation AUTO = new ImageResourceLocation(COMMON_ATLAS, 32, 0, 16, 16);
    public static final ImageResourceLocation INV = new ImageResourceLocation(COMMON_ATLAS, 0, 128, 16, 16);
    public static final ImageResourceLocation NOINV = new ImageResourceLocation(COMMON_ATLAS, 16, 128, 16, 16);
    public static final ImageResourceLocation FORBID = new ImageResourceLocation(COMMON_ATLAS, 0, 0, 16, 16);

    public static final ImageResourceLocation ENERGY_BAR_BG = new ImageResourceLocation(COMMON_ATLAS, 238, 0, 18, 60);
    //POTIONS
    private static final String ICONS_POTION= LibMod.MODID + ":textures/gui/icons_potion.png";
    public static final ImageResourceLocation LUMBERING = new ImageResourceLocation(ICONS_POTION, 0, 0, 18, 18);
    public static final ImageResourceLocation SPELUNKING = new ImageResourceLocation(ICONS_POTION, 18, 0, 18, 18);
    public static final ImageResourceLocation THORNS = new ImageResourceLocation(ICONS_POTION, 36, 0, 18, 18);
    public static final ImageResourceLocation GREENTHUMB = new ImageResourceLocation(ICONS_POTION, 54, 0, 18, 18);
    public static final ImageResourceLocation HARVESTLUCK = new ImageResourceLocation(ICONS_POTION, 72, 0, 18, 18);
    public static final ImageResourceLocation BANDAGED = new ImageResourceLocation(ICONS_POTION, 90, 0, 18, 18);
}
