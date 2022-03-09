package basicessentials.basicessentials.Ranks;

import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import basicessentials.basicessentials.Utils.SQLUtils;
import basicessentials.basicessentials.Utils.RankUtils;
import basicessentials.basicessentials.Listeners.OnJoin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("rank")) {
                if (PermUtils.PermChecker(player, "RankP")) {
                    if (args.length == 0) {
                        RankUtils.RankHelp(player);
                    } else if (args.length >= 1) {
                        if (args[0].equalsIgnoreCase("create")) {
                            if (args.length <= 3) {
                                RankUtils.RankHelp(player); // Not enough args supplied
                            } else if (args.length > 4) { // To many args supplied
                                ChatUtils.Args(player);
                            } else { // Correct args supplied
                                String rankname = args[1];
                                String rankprefix = args[2];
                                try {
                                    String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                                    ResultSet rs = SQLUtils.preparedStatement(s).executeQuery();
                                    if (rs.next()) { // If rank already exists
                                        ChatUtils.ConfigMessage(player, "RankExists");
                                    } else { // If rank does not exist
                                        try {
                                            String weight = args[3].toString();
                                            SQLUtils.PrepNom("INSERT INTO `ranks`(`name`, `prefix`, `weight`, `permissions`) VALUES ('" + rankname + "','" + rankprefix + "','" + weight + "','test.node')");
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                    }
                                } catch (SQLException x) {
                                    x.printStackTrace();
                                }
                            }
                        } else if (args[0].equalsIgnoreCase("delete")) {
                            if (args.length >= 3) { // To many args supplied
                                ChatUtils.Args(player);
                            } else if (args[1].equalsIgnoreCase("Default")) {
                                ChatUtils.ConfigMessage(player, "DeleteDefault");
                            } else {
                                try {
                                    String rankname = args[1];
                                    String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                                    ResultSet rs = SQLUtils.preparedStatement(s).executeQuery();
                                    if (rs.next()) { // If rank is in database
                                        SQLUtils.PrepNom("DELETE from `ranks` WHERE name = '" + rankname + "';"); // delete the rank
                                    } else {
                                        ChatUtils.ConfigMessage(player, "RankExists");
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }
                        } else if (args[0].equalsIgnoreCase("rename")) {
                            if (args[1].equalsIgnoreCase("Default")) {
                                ChatUtils.ConfigMessage(player, "RenameDefault");
                            } else if (args.length == 3) {
                                try {
                                    String rankname = args[1];
                                    String newrankname = args[2];

                                    String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                                    ResultSet rs = SQLUtils.preparedStatement(s).executeQuery();
                                    if (rs.next()) { // If rank is in database
                                        SQLUtils.PrepNom("UPDATE `pinfo` SET `rank` = '" + newrankname + "' WHERE rank = '" + rankname + "';"); // Moves people to new rank who where in old
                                        SQLUtils.PrepNom("UPDATE `ranks` SET `name` = '" + newrankname + "' WHERE name = '" + rankname + "';"); // Renames the rank
                                    } else {
                                        ChatUtils.ConfigMessage(player, "RankNotExist");
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            } else  { // To many args supplied
                                ChatUtils.Args(player);
                            }
                        } else if (args[0].equalsIgnoreCase("setprefix")) {
                            if (args.length > 3) {
                                ChatUtils.Args(player);
                            } else if (args.length <= 2) {
                                RankUtils.RankHelp(player);
                            } else {
                                try {
                                    String rankname = args[1];
                                    String newprefix = args[2];

                                    String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                                    ResultSet rs = SQLUtils.preparedStatement(s).executeQuery();
                                    try {
                                        if (rs.next()) { // If rank is in database
                                            SQLUtils.PrepNom("UPDATE `ranks` SET `prefix` = '" + newprefix + "' WHERE name = '" + rankname + "';"); // Renames the rank
                                            ChatUtils.ConfigMessage(player, "RenamePrefix");
                                        } else {
                                            ChatUtils.ConfigMessage(player, "RankNotExist");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }

                        } else if (args[0].equalsIgnoreCase("setweight")) { // ReWeight a rank

                            if (args.length > 3) {
                                ChatUtils.Args(player);
                            } else if (args.length <= 2) {
                                RankUtils.RankHelp(player);
                            } else {
                                String rankname = args[1];
                                int newweight = Integer.parseInt(args[2]);

                                String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                                ResultSet rs = null;
                                try {
                                    rs = SQLUtils.preparedStatement(s).executeQuery();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    if (rs.next()) { // If rank is in database
                                        SQLUtils.PrepNom("UPDATE `ranks` SET `weight` = '" + newweight + "' WHERE name = '" + rankname + "';"); // Updates the weight
                                        ChatUtils.ConfigMessage(player, "SetWeight");
                                    } else {
                                        ChatUtils.ConfigMessage(player, "RankNotExist");
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (args[0].equalsIgnoreCase("addperm")) {
                            if (args.length > 3) {
                                ChatUtils.Args(player);
                            } else if (args.length <= 2) {
                                RankUtils.RankHelp(player);
                            } else {

                                // MAKE THIS A FUNCTION

                                String rankname = args[1];
                                String newpermission = args[2];

                                String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                                ResultSet rs = null;
                                try {
                                    rs = SQLUtils.preparedStatement(s).executeQuery();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    if (rs.next()) { // If rank is in database
                                        String permission = rs.getString("permissions");
                                        permission += "," + args[2];
                                        SQLUtils.PrepNom("UPDATE `ranks` SET `permissions` = '" + permission + "' WHERE name = '" + rankname + "';");
                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                            PermissionAttachment attachment = OnJoin.permattach.get(player.getUniqueId());
                                            attachment.setPermission(newpermission, true);
                                        }

                                    } else {
                                        ChatUtils.ConfigMessage(player, "RankNotExist");
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (args[0].equalsIgnoreCase("delperm")) {
                            if (args.length > 3) {
                                ChatUtils.Args(player);
                            } else if (args.length <= 2) {
                                RankUtils.RankHelp(player);
                            } else {

                                // MAKE THIS A FUNCTION

                                String rankname = args[1];
                                String newpermission = args[2];

                                String s = "SELECT * FROM `ranks` WHERE name = '" + rankname + "';";
                                ResultSet rs = null;
                                try {
                                    rs = SQLUtils.preparedStatement(s).executeQuery();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    if (rs.next()) { // If rank is in database
                                        String permission = rs.getString("permissions");
                                        permission = permission.replace("," + args[2] + ",", ",");
                                        SQLUtils.PrepNom("UPDATE `ranks` SET `permissions` = '" + permission + "' WHERE name = '" + rankname + "';");
                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                            PermissionAttachment attachment = OnJoin.permattach.get(player.getUniqueId());
                                            attachment.setPermission(newpermission, true);
                                        }

                                    } else {
                                        ChatUtils.ConfigMessage(player, "RankNotExist");
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}

