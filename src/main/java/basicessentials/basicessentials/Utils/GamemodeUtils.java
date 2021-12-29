package basicessentials.basicessentials.Utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/*
This class is used to handle all gamemode functions.
 */

public class GamemodeUtils { // Switches "x" players gamemode
    public static void gamemodeswitcher(Player player, GameMode gamemode, String messagepath) {
        player.setGameMode(gamemode);
        ChatUtils.ConfigMessage(player, "gmcm");
    }

}
