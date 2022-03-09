package basicessentials.basicessentials.Ranks.SubCommands;

import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import basicessentials.basicessentials.Utils.SQLUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rankinfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        PermUtils.PermChecker(player, "rankinfoperm");

        if(args.length == 0) {
            ChatUtils.Args(player);
        } else if (args.length <= 1) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if(target.hasPlayedBefore()) { // Should be in database no need to do a lookup
                ResultSet uuid = SQLUtils.ExecuteResultStatement("SELECT uuid FROM pinfo WHERE uuid = '" + target.getUniqueId() +"';");
                ResultSet rank = SQLUtils.ExecuteResultStatement("SELECT rank FROM pinfo WHERE uuid = '" + target.getUniqueId() +"';");
                ResultSet rankreason = SQLUtils.ExecuteResultStatement("SELECT rankreason FROM pinfo WHERE uuid = '" + target.getUniqueId() +"';");
                ResultSet rankbefore = SQLUtils.ExecuteResultStatement("SELECT rankbefore FROM pinfo WHERE uuid = '" + target.getUniqueId() +"';");
                try  {
                    if(uuid.next()) {
                        if(rank.next()) {
                            if (rankreason.next()) {
                                if (rankbefore.next()) { // Need to add configuration
                                    ChatUtils.Chat(player, "&7&M___________________________");
                                    ChatUtils.Chat(player, "");
                                    ChatUtils.Chat(player, "&cUUID &7: " + uuid.getString("uuid"));
                                    ChatUtils.Chat(player, "&cRANK &7: " + rank.getString("rank"));
                                    ChatUtils.Chat(player, "&cRANK REASON &7: " + rankreason.getString("rankreason"));
                                    ChatUtils.Chat(player, "&cPREVIOUS RANK &7: " + rankbefore.getString("rankbefore"));
                                    ChatUtils.Chat(player, "");
                                    ChatUtils.Chat(player, "&7&m___________________________");
                                }
                            }
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else {
                ChatUtils.Chat(player, "&cCould not find the player in the database? &7(&cIf you are using bungeecord try using the hub server&7)");
            }
        } else {
            ChatUtils.Args(player);
        }

        return false;
    }
}
