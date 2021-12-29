package basicessentials.basicessentials.Listeners;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.SQLUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OnChat implements Listener {



    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player player = e.getPlayer();
        UUID PlayerUUID = e.getPlayer().getUniqueId();
        try {
            String p = "SELECT * FROM `pinfo` WHERE uuid = '" + player.getUniqueId().toString() + "';";
            ResultSet rs = SQLUtils.preparedStatement(p).executeQuery();
            if (rs.next()) { // Get the row I think
                String playerrank = rs.getString("rank");
                try {
                    String r = "SELECT * FROM `ranks` WHERE name = '" + playerrank + "';";
                    ResultSet res = SQLUtils.preparedStatement(r).executeQuery();
                    if (res.next()) { // Get the row I think
                        String rankprefix = res.getString("prefix");
                        e.setCancelled(true);
                        for (Player onlinePlayers : e.getRecipients()) {
                            // "%rank% %name%: %message%"
                            // rankprefix) + player.getDisplayName() + ": " + e.getMessage());
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', BasicEssentials.getInstance().getConfig().getString("Chat-Format").replace("%rank%", rankprefix).replace("%name%", player.getDisplayName()).replace("%message%", e.getMessage())));
                        }
                    }
                } catch (SQLException x) {
                    x.printStackTrace();
                }
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }
}
