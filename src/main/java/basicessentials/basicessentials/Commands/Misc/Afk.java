package basicessentials.basicessentials.Commands.Misc;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player player = (Player) sender;

        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("afk")) {
                if (PermUtils.PermChecker(player, "afkp")) {
                    switch (args.length) {
                        case 0:
                            if (BasicEssentials.afk.contains(player.getPlayer())) {
                                BasicEssentials.afk.remove(player.getPlayer());
                                ChatUtils.ConfigMessage(player, "afkmn");
                            } else {
                                BasicEssentials.afk.add(player.getPlayer());
                                ChatUtils.ConfigMessage(player, "afkmy");
                            }
                            break;
                        case 1:
                            OfflinePlayer playerarg1 = Bukkit.getPlayer(args[0]);
                            if (playerarg1.isOnline()) {
                                if (BasicEssentials.afk.contains(playerarg1)) {
                                    BasicEssentials.afk.remove(playerarg1);
                                    ChatUtils.ConfigMessage(playerarg1.getPlayer(), "afkmn");
                                } else { //
                                    BasicEssentials.afk.add((Player) playerarg1);
                                    ChatUtils.ConfigMessage(playerarg1.getPlayer(), "afkmy");
                                }
                            }
                            break;
                        default:
                            ChatUtils.Args(player);
                            break;
                    }
                }
            } else {
                ChatUtils.Console();
            }
        }
        return false;
    }
}
