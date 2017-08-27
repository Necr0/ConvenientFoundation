package convenientfoundation.item.base;

import convenientfoundation.libs.LibMod;
import convenientfoundation.util.Helper;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by Necro on 3/8/2017.
 */
public enum EnumItemCategory {
    CRAFTING_MATERIAL("craftingMaterial"),
    TRINKET("trinket"),
    RELIC("relic"),
    TOOL("tool"),
    CONSUMABLE("consumable"),
    MACHINE("machine"),
    MODULE("module"),
    MISC("misc"),
    BUILDING_BLOCK("buildingBlock");

    public String name;

    EnumItemCategory(String name){
        this.name=name;
    }

    public String getSuffix(){
        return name;
    }

    public String getUnlocalizedName(){
        return "tooltip."+ LibMod.MODID+":itemCategory."+getSuffix();
    }

    public String getLocalizedName(){
        return Helper.localize(getUnlocalizedName());
    }

    public void addTooltip(List<String> tooltips){
        tooltips.add(Helper.localize("tooltip."+ LibMod.MODID+":itemCategory", TextFormatting.DARK_PURPLE+getLocalizedName()));
    }
}
