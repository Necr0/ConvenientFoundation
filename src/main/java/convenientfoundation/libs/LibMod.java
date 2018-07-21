package convenientfoundation.libs;

/**
 * Created by Necro on 8/26/2017.
 */
public class LibMod {
    public static final String MODNAME = "Convenient Foundation";
    public static final String MODID = "convfound";
    public static final String BUILD = "GRADLE:BUILD";
    public static final String VERSION = "GRADLE:VERSION-" + BUILD;
    public static final String DEPENDENCIES = "required-after:forge@[14.21.1.2415 ,);after:baubles";
    public static final String commonProxyClassPath = "convenientfoundation.proxy.CommonProxy";
    public static final String clientProxyClassPath = "convenientfoundation.proxy.ClientProxy";
}
