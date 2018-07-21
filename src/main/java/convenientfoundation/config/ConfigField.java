package convenientfoundation.config;

import net.minecraftforge.common.config.Configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class ConfigField {
    Field field;
    String category;
    String comment;

    public ConfigField(Field f, String cat){
        field=f;
        category=cat;
        if(f.isAnnotationPresent(Config.Comment.class))
            comment=f.getAnnotation(Config.Comment.class).value();
    }

    public String getCategory(){
        return category;
    }

    public String getName(){
        return field.getName();
    }

    public abstract void readField(Configuration cfg);

    public static class BooleanField extends ConfigField {
        boolean default_value;

        public BooleanField(Field f, String cat) {
            super(f, cat);
            try {
                default_value=f.getBoolean(null);
            } catch (IllegalAccessException ignored) {}
        }

        @Override
        public void readField(Configuration cfg) {
            try {
                field.set(null,cfg.getBoolean(getName(),getCategory(),default_value,comment));
            } catch (IllegalAccessException ignored) {}
        }
    }

    public static class IntegerField extends ConfigField {
        int default_value;
        int min=Integer.MIN_VALUE, max=Integer.MAX_VALUE;

        public IntegerField(Field f, String cat) {
            super(f, cat);
            try {
                default_value=f.getInt(null);
            } catch (IllegalAccessException ignored) {}
            if(f.isAnnotationPresent(Config.IntRange.class)){
                Config.IntRange ann=f.getAnnotation(Config.IntRange.class);
                min=ann.min();
                max=ann.max();
            }
        }

        @Override
        public void readField(Configuration cfg) {
            try {
                field.set(null,cfg.getInt(getName(),getCategory(),default_value,min,max,comment));
            } catch (IllegalAccessException ignored) {}
        }
    }

    public static class DecimalField extends ConfigField {
        float default_value;
        float min=Float.MIN_VALUE, max=Float.MAX_VALUE;

        public DecimalField(Field f, String cat) {
            super(f, cat);
            try {
                default_value=f.getFloat(null);
            } catch (IllegalAccessException ignored) {}
            if(f.isAnnotationPresent(Config.DecimalRange.class)){
                Config.DecimalRange ann=f.getAnnotation(Config.DecimalRange.class);
                min=ann.min();
                max=ann.max();
            }else if(f.isAnnotationPresent(Config.IntRange.class)){
                Config.IntRange ann=f.getAnnotation(Config.IntRange.class);
                min=ann.min();
                max=ann.max();
            }
        }

        @Override
        public void readField(Configuration cfg) {
            try {
                field.set(null,cfg.getFloat(getName(),getCategory(),default_value,min,max,comment));
            } catch (IllegalAccessException ignored) {}
        }
    }

    public static class StringField extends ConfigField {
        String default_value;

        public StringField(Field f, String cat) {
            super(f, cat);
            try {
                default_value=(String)f.get(null);
            } catch (IllegalAccessException ignored) {}
        }

        @Override
        public void readField(Configuration cfg) {
            try {
                field.set(null,cfg.getString(getName(),getCategory(),default_value,comment));
            } catch (IllegalAccessException ignored) {}
        }
    }

    public static class StringListField extends ConfigField {
        String[] default_value;
        String[] valid_strings;

        @SuppressWarnings("unchecked")
        public StringListField(Field f, String cat) {
            super(f, cat);
            try {
                if(f.getType()==String[].class){
                    default_value=(String[])f.get(null);
                }else{
                    List<String> list=(List<String>)f.get(null);
                    default_value=list.toArray(new String[list.size()]);
                }
            } catch (IllegalAccessException ignored) {}
            if(f.isAnnotationPresent(Config.ValidStrings.class))
                valid_strings=f.getAnnotation(Config.ValidStrings.class).value();
        }

        @Override
        public void readField(Configuration cfg) {
            try {
                String[] result;
                if(valid_strings!=null)
                    result=cfg.getStringList(getName(),getCategory(),default_value,comment,valid_strings);
                else
                    result=cfg.getStringList(getName(),getCategory(),default_value,comment);

                if(field.getType()==String[].class){
                    field.set(null,result);
                }else{
                    field.set(null, Arrays.asList(result));
                }
            } catch (IllegalAccessException ignored) {}
        }
    }
}
