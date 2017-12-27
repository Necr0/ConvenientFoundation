package convenientfoundation.util;

import net.minecraft.entity.player.EntityPlayer;

public class ExperienceHelper {

    public static int xpBarCap(EntityPlayer player,int level)
    {
        int real_level=player.experienceLevel;
        player.experienceLevel=level;
        int cap=player.xpBarCap();
        player.experienceLevel=real_level;
        return cap;
    }

    public static void addCurrentLevelXp(EntityPlayer player, int amount){
        player.experience=((int)(player.xpBarCap()*player.experience)+amount)/(float)player.xpBarCap();
        player.experienceTotal+=amount;
    }

    public static int getCurrentLevelXp(EntityPlayer player){
        return (int)(player.xpBarCap()*player.experience);
    }

    public static void drainExperience(EntityPlayer player, int amount)
    {
        int player_amount;
        while (amount>0){
            player_amount=getCurrentLevelXp(player);
            if(player_amount-amount<0){
                if(player.experienceLevel>0) {
                    player.experienceLevel--;
                    player.experienceTotal -= player_amount;
                    player.experience = 1F;
                    amount -= player_amount;
                }else{
                    player.experienceLevel=0;
                    player.experience=0;
                    player.experienceTotal=0;
                    return;
                }
            }
            else
            {
                addCurrentLevelXp(player, -amount);
                return;
            }
        }
    }
}
