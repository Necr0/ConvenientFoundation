package convenientfoundation.config;

import convenientfoundation.ConvenientFoundation;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ConfigFile {
    private String modid;
    private Configuration cfg;

    public List<ConfigField> fields=new ArrayList<>();

    public ConfigFile(String modid){
        this.modid=modid;
    }

    public void addField(Field f, String category){
        Class<?> type=f.getType();
        if(type==boolean.class){
            fields.add(new ConfigField.BooleanField(f,category));
        }else if(type==int.class){
            fields.add(new ConfigField.IntegerField(f,category));
        }else if(type==float.class||type==double.class){
            fields.add(new ConfigField.DecimalField(f,category));
        }else if(type==String.class){
            fields.add(new ConfigField.StringField(f,category));
        }else if(type==String[].class||type==List.class){
            fields.add(new ConfigField.StringListField(f,category));
        }
    }

    public void parseConfig(Class clazz){
        if(!clazz.isAnnotationPresent(Config.class))
            return;
        Config ann=(Config)clazz.getAnnotation(Config.class);
        if(modid==null)
            modid=ann.modid();
        String category=ann.category();
        Arrays.stream(clazz.getFields())
                .filter((f)-> Modifier.isStatic(f.getModifiers()))
                .forEach((f)->addField(f,category));
    }

    public void loadConfig(){
        if(ConfigHandler.config_directory==null)
            return;
        cfg=new Configuration(new File(ConfigHandler.config_directory,modid+".cfg"));
        cfg.load();
        for (ConfigField f:fields) {
            f.readField(cfg);
        }
        if(cfg.hasChanged())
            cfg.save();
    }

    public String getModid() {
        return modid;
    }
}
