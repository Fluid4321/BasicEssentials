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
import java.util.UUID;

public class SetRank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("setrank")) {
                if (PermUtils.PermChecker(player, "RankP")) {
                    if (args.length == 0) {
                        ChatUtils.Chat(player, "&cCorrect usage: /setrank rank player");
                    } else if (args.length == 2) {
                        String rankname = args[0];
                        try {
                            String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                            ResultSet rs = SQLUtils.preparedStatement(s).executeQuery();
                            if (rs.next()) { // If rank already exists
                                Boolean rankexists = true;
                                OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
                                if (op.hasPlayedBefore()) {
                                    UUID uuid = op.getUniqueId();
                                    try {
                                        String ss = "SELECT * FROM `pinfo` WHERE uuid = '" + player.getUniqueId().toString() + "';";
                                        ResultSet rss = SQLUtils.preparedStatement(ss).executeQuery();
                                        if (!rss.next()) { // If the user isn't in the database add them.
                                            System.out.println("They are not in the database somehow, something has gone seriously wrong wtf?");
                                        } else { // If user is in database
                                            SQLUtils.PrepNom("UPDATE `pinfo` SET `rank` = '" + rankname + "' WHERE uuid = '" + uuid + "';");
                                        }
                                    } catch (SQLException x) {
                                        x.printStackTrace();
                                    }

                                } else {
                                    ChatUtils.ConfigMessage(player, "UserNeverJoined");
                                }
                            }
                        } catch (SQLException x) {
                            x.printStackTrace();
                        }

                    } else {
                        ChatUtils.Args(player);
                    }
                }
            }
        return false;
    }
}