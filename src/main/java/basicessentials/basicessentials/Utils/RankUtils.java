package basicessentials.basicessentials.Utils;

import basicessentials.basicessentials.BasicEssentials;
import org.bukkit.entity.Player;

import java.util.List;

/*
This class is used to handle all rank functions.
 */


public class RankUtils {

    public static void RankHelp(Player player) { // Displays the configured rank help message
        List<String> list = BasicEssentials.getInstance().getConfig().getStringList("RankHelpMessage");
        for (String line : list) {
            ChatUtils.Chat(player, line);
    }
    }

}
