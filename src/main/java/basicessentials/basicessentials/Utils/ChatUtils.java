package basicessentials.basicessentials.Utils;

import basicessentials.basicessentials.BasicEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

/*
This class is used to handle all chat functions.
 */

public class ChatUtils {

    public static void Chat(Player player, String text) { // Easy way to handle sending chat messages
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public static void ConfigMessage(Player player, String configpath) { // Gets a message from config and adds color code support
        Chat(player, BasicEssentials.getInstance().getConfig().getString(configpath));
    }

    public static void Console() { // Sends error: User isn't player
        System.out.println(BasicEssentials.getInstance().getConfig().getString("NotPlayer"));
    }

    public static void Args(Player player) { // Sends error: To many args
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(BasicEssentials.getInstance().getConfig().getString("Args"))));
    }

    public static void NotOnline(Player player) { // Sends error: User isn't online
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(BasicEssentials.getInstance().getConfig().getString("UserNotOnline"))));
    }

    public static void NoPerm(Player player) { // Sends error: No permission
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(BasicEssentials.getInstance().getConfig().getString("NoPerm"))));
    }

    public static void BroadcastConfig(String configpath) { // Broadcasts x message from config
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', BasicEssentials.getInstance().getConfig().getString(configpath)));
    }
}
