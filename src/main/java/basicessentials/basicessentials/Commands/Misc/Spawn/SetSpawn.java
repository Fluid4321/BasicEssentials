package basicessentials.basicessentials.Commands.Misc.Spawn;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("setspawn")) {
                if (PermUtils.PermChecker(player, "SetSpawnPermission")) {
                    switch (args.length) {
                        case 0:
                            Location location = player.getLocation();
                            BasicEssentials.getInstance().getConfig().set("Spawn.World", player.getLocation().getWorld().getName());
                            BasicEssentials.getInstance().getConfig().set("Spawn.X", Double.valueOf(player.getLocation().getX()));
                            BasicEssentials.getInstance().getConfig().set("Spawn.Y", Double.valueOf(player.getLocation().getY()));
                            BasicEssentials.getInstance().getConfig().set("Spawn.Z", Double.valueOf(player.getLocation().getZ()));
                            BasicEssentials.getInstance().saveConfig();
                            ChatUtils.ConfigMessage(player, "Spawn.SetSpawn");
                    }
                }
            }
        }
        return false;
    }
}
