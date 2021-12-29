package basicessentials.basicessentials.Commands.Misc;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {

    public final BasicEssentials basicEssentials;

    public Kill(BasicEssentials basicEssentials) {
        this.basicEssentials = basicEssentials;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("kill")) {
                if (PermUtils.PermChecker(player, "killps") || (PermUtils.PermChecker(player, "killpo"))) {
                    switch (args.length) {
                        case 0:
                            if (PermUtils.PermChecker(player, "killps")) {
                                player.setHealth(0);
                                ChatUtils.ConfigMessage(player, "killselfm");
                            }
                            break;
                        case 1:
                            if (PermUtils.PermChecker(player, "killpo")) {
                                Bukkit.getServer().getPlayer(args[0]).setHealth(0);
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', basicEssentials.getConfig().getString("killotherm").replace("{player}", Bukkit.getServer().getPlayer(args[0]).getDisplayName())));
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

