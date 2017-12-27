package convenientfoundation.common.item.base;

import convenientfoundation.libs.LibMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by Necro on 3/8/2017.
 */
public enum EnumItemCategory {
    RESOURCE("resource"),
    CRAFTING_MATERIAL("crafting_material"),
    TRINKET("trinket"),
    RELIC("relic"),
    TOOL("tool"),
    CONSUMABLE("consumable"),
    MACHINE("machine"),
    MODULE("module"),
    MISC("misc"),
    BUILDING_BLOCK("building_block"),
    DEBUG("debug");

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
        return I18n.format(getUnlocalizedName());
    }

    public void addTooltip(List<String> tooltips){
        tooltips.add(I18n.format("tooltip."+ LibMod.MODID+":itemCategory", TextFormatting.DARK_PURPLE+getLocalizedName()));
    }
}
