package convenientfoundation.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.fml.common.Loader;

import java.util.Random;

public class ConditionModLoaded implements LootCondition {

    private final boolean inverse;
    private final String modid;

    public ConditionModLoaded(String modid){
        this(modid,false);
    }

    public ConditionModLoaded(String modid,boolean inverse)
    {
        this.modid = modid;
        this.inverse = inverse;
    }

    @Override
    public boolean testCondition(Random rand, LootContext context) {
        return Loader.isModLoaded(modid)^inverse;
    }

    public static class Serializer extends LootCondition.Serializer<ConditionModLoaded>
    {
        protected Serializer()
        {
            super(new ResourceLocation("killed_by_player"), ConditionModLoaded.class);
        }

        public void serialize(JsonObject json, ConditionModLoaded value, JsonSerializationContext context)
        {
            json.addProperty("modid", value.modid);
            json.addProperty("inverse", value.inverse);
        }

        public ConditionModLoaded deserialize(JsonObject json, JsonDeserializationContext context)
        {
            return new ConditionModLoaded(JsonUtils.getString(json, "modid"),JsonUtils.getBoolean(json, "inverse", false));
        }
    }
}
