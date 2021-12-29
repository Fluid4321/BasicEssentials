package basicessentials.basicessentials.Utils;

import basicessentials.basicessentials.BasicEssentials;
import org.bukkit.entity.Player;

/*
This class is used to handle all permission functions.
 */

public class PermUtils {

    public static boolean PermChecker(Player player, String permission) { // Checks if player has "x" permission returns boolean (true/false).
        if (player.hasPermission(BasicEssentials.getInstance().getConfig().getString(permission))) {
            return true;
        } else {
            ChatUtils.NoPerm(player);
            return false;
        }
    }



}
