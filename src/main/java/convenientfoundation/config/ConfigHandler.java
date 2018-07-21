package convenientfoundation.config;

import com.google.common.reflect.Reflection;
import convenientfoundation.ConvenientFoundation;

import javax.annotation.Nullable;
import java.io.File;
import java.net.URL;
import java.util.*;

public class ConfigHandler {
    @Nullable
    public static File config_directory=null;

    private static List<Class> config_classes=new ArrayList<>();

    private static Map<String,ConfigFile> configs=new HashMap<>();

    public static void registerClasses(Class... clazz){
        config_classes.addAll(Arrays.asList(clazz));
    }

    public static void registerClassesRecursive(Class... clazz){
        config_classes.addAll(Arrays.asList(clazz));
        for (Class c:clazz) {
            registerClassesRecursive(c.getClasses());
        }
    }

    public static void parseConfigs(){
        for(Class clazz:config_classes){
            if(!clazz.isAnnotationPresent(Config.class))
                continue;
            Config ann=(Config)clazz.getAnnotation(Config.class);
            String modid=ann.modid();
            if(!configs.containsKey(modid)){
                ConfigFile file=new ConfigFile(modid);
                configs.put(modid,file);
            }
            ConfigFile file=configs.get(modid);
            file.parseConfig(clazz);
        }
    }

    public static void loadConfigs(){
        ConvenientFoundation.LOG.info("###Loading {} Configurations###",configs.size());
        configs.values().forEach((f)->{
            f.loadConfig();
            ConvenientFoundation.LOG.info("Loaded Configuration {}",f.getModid());
        });
        ConvenientFoundation.LOG.info("###Finished Loading Configurations###",configs.size());
    }
}
