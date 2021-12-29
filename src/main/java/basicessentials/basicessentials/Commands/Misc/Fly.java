package basicessentials.basicessentials.Commands.Misc;

import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("fly")) {
                if (PermUtils.PermChecker(player, "flyp")) {
                    switch (args.length) {
                        case 0:
                            if (!player.getAllowFlight()) {
                                player.setAllowFlight(true);
                                ChatUtils.ConfigMessage(player, "flyym");
                            } else {
                                player.setAllowFlight(false);
                                ChatUtils.ConfigMessage(player, "flynm");
                            }
                            break;
                        case 1:
                            if (!Bukkit.getServer().getPlayer(args[0]).getAllowFlight()) {
                                {
                                    Bukkit.getServer().getPlayer(args[0]).setAllowFlight(true);
                                    ChatUtils.ConfigMessage(player, "flyym");
                                }
                            } else {
                                Bukkit.getServer().getPlayer(args[0]).setAllowFlight(false);
                                ChatUtils.ConfigMessage(player, "flynm");
                            }
                            break;
                        default:
                            ChatUtils.Args(player);
                            break;
                    }
                }
            }
        }
        return false;
    }
}

