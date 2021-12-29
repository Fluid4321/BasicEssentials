package basicessentials.basicessentials.Commands.Misc;

import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("heal")) {
                if (PermUtils.PermChecker(player, "healps") || (PermUtils.PermChecker(player, "healpo"))) {
                    switch (args.length) {
                        case 0:
                            if (PermUtils.PermChecker(player, "healps")) {
                                player.setHealth(20);
                                ChatUtils.ConfigMessage(player, "healm");
                            }
                            break;
                        case 1:
                            if (PermUtils.PermChecker(player, "healpo")) {
                                Bukkit.getServer().getPlayer(args[0]).setHealth(20);
                                ChatUtils.ConfigMessage(player, "healm");
                            }
                            break;
                        default:
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "To many arguments supplied"));
                    }
                }
            }
        }
        return false;
    }
}
