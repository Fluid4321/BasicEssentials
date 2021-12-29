package basicessentials.basicessentials.Listeners;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.SQLUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class OnJoin implements Listener {

    public final BasicEssentials basicEssentials;

    public OnJoin(BasicEssentials basicEssentials) {
        this.basicEssentials = basicEssentials;
    }

    public static HashMap<UUID, PermissionAttachment> permattach = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws SQLException {
        Player player = e.getPlayer();

        try {
            String s = "SELECT * FROM `pinfo` WHERE uuid = '" + player.getUniqueId().toString() + "';";
            ResultSet rs = SQLUtils.preparedStatement(s).executeQuery();
            if (!rs.next()) { // If the user isn't in the database add them.
                SQLUtils.PrepNom("INSERT INTO `pinfo`(uuid, firstjoindate, lastjoindate, rank, rankperm, rankexpirytime, rankreason, rankbefore) VALUES ('" + player.getUniqueId().toString() + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'DEFAULT', TRUE, NULL, 'N/A', 'N/A');");
            } else { // If user is in database
                SQLUtils.PrepNom("UPDATE `pinfo` SET `lastjoindate` = CURRENT_TIMESTAMP WHERE uuid = '" + player.getUniqueId().toString() + "';");
            }
        } catch (SQLException x) {
            x.printStackTrace();
        }

        PermissionAttachment attachment = player.addAttachment(BasicEssentials.getInstance());
        permattach.put(player.getUniqueId(), attachment);
        String s = "SELECT * FROM `pinfo` WHERE uuid = '" + player.getUniqueId().toString() + "';";
        ResultSet rs = SQLUtils.preparedStatement(s).executeQuery();
        rs.next();
        String rank = rs.getString("rank");
        String ss = "SELECT * FROM `ranks` WHERE name = '" + rank + "';";
        ResultSet rss = SQLUtils.preparedStatement(ss).executeQuery();
        rss.next();
        String permission = rss.getString("permissions");
        String[] permissionlist = permission.split(",");
        for (String perm:permissionlist
             ) {
            attachment.setPermission(perm, true);
        }

        if (basicEssentials.getConfig().getBoolean("AnnounceWelcomes")) {
            List<String> list = basicEssentials.getConfig().getStringList("WelcomeAnnouncements");
            for (String line : list) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', line).replace("{player}", e.getPlayer().getDisplayName()));
            }
        }
        if (basicEssentials.getConfig().getBoolean("WelcomerEnabled")) {
            List<String> list = basicEssentials.getConfig().getStringList("WelcomeMessage");
            for (String line : list) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', line).replace("{player}", e.getPlayer().getDisplayName()));
            }
            if (basicEssentials.afk.contains(e.getPlayer())) {
                basicEssentials.afk.remove(e.getPlayer());
            }
        }
    }
}

