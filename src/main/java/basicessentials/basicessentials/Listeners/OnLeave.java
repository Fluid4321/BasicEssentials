package basicessentials.basicessentials.Listeners;

import basicessentials.basicessentials.BasicEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class OnLeave implements Listener {

    public final BasicEssentials basicEssentials;

    public OnLeave(BasicEssentials basicEssentials) {
        this.basicEssentials = basicEssentials;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if(basicEssentials.getConfig().getBoolean("LeaverEnabled")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', basicEssentials.getConfig().getString("LeaveMessage").replace("{player}", e.getPlayer().getDisplayName())));
            basicEssentials.afk.remove(e.getPlayer());
        }
    }
}

