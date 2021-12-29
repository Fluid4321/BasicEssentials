package basicessentials.basicessentials.Commands.Misc.Spawn;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("spawn")) {
                if (PermUtils.PermChecker(player, "SpawnPermission")) {
                    if (args.length == 0) {
                            World w = Bukkit.getServer().getWorld(BasicEssentials.getInstance().getConfig().getString("Spawn.World"));
                            double x = BasicEssentials.getInstance().getConfig().getDouble("Spawn.X");
                            double y = BasicEssentials.getInstance().getConfig().getDouble("Spawn.Y");
                            double z = BasicEssentials.getInstance().getConfig().getDouble("Spawn.Z");
                            Location loc = new Location(w, x, y, z);
                            player.teleport(loc);
                        ChatUtils.ConfigMessage(player, "Spawn.TPSpawn");
                    } else {
                        ChatUtils.Args(player);
                    }
                }
            }
        }
        return false;
    }
}
