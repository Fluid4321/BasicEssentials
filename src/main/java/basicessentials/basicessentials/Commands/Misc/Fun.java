package basicessentials.basicessentials.Commands.Misc;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Fun implements CommandExecutor {

    public final BasicEssentials basicEssentials;

    public Fun(BasicEssentials basicEssentials) {
        this.basicEssentials = basicEssentials;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender != null) {
            if (PermUtils.PermChecker(player, "funp")) {
                Location lxyz = player.getLocation();
                ChatUtils.Chat(player, basicEssentials.getConfig().getString("beginfun"));
                for (int i = 0; i < 5; i++) {
                    player.getWorld().strikeLightning(lxyz);
                }
                for (int i = 0; i < 25; i++) {
                    Bee bee = (Bee) player.getWorld().spawnEntity(lxyz, EntityType.BEE);
                    bee.setCustomNameVisible(true);
                    bee.setCustomName("The Individual Bee");
                    bee.setAnger(1000000000);
                    bee.setTarget(player.getPlayer());
                    bee.setFireTicks(0);
                    ChatUtils.Chat(player, "&c");
                }
                player.getWorld().spawnEntity(lxyz, EntityType.FIREWORK);
            } else {
                ChatUtils.NoPerm(player);
            }
        } else {
            ChatUtils.Args(player);
        }


        return false;
    }
}
