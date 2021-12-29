package basicessentials.basicessentials.Commands.Gamemode;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.GamemodeUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Gamemode implements CommandExecutor {

    public final BasicEssentials basicEssentials;

    public Gamemode(BasicEssentials basicEssentials) {
        this.basicEssentials = basicEssentials;
    }


    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;


        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("gamemode")) {
                if (PermUtils.PermChecker(player, "gmp")) {
                    switch (args.length) {
                        case 0:
                            List<String> list = basicEssentials.getConfig().getStringList("gamemodehelp");
                            for (String line : list) {
                                ChatUtils.Chat(player, line.replace("{player}", player.getPlayer().getDisplayName()));
                            }
                            return true;
                        case 1:
                            if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")) {
                                GamemodeUtils.gamemodeswitcher(player, GameMode.CREATIVE, "gmcm");
                            }
                            if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")) {
                                GamemodeUtils.gamemodeswitcher(player, GameMode.SURVIVAL, "gmsm");
                            }
                            if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")) {
                                GamemodeUtils.gamemodeswitcher(player, GameMode.ADVENTURE, "gmam");
                            }
                            if (args[0].equalsIgnoreCase("spec") || args[0].equalsIgnoreCase("spectator")) {
                                GamemodeUtils.gamemodeswitcher(player, GameMode.SPECTATOR, "gmspecm");
                            }
                            break;
                        case 2:
                            OfflinePlayer target = Bukkit.getServer().getPlayer(args[1]);
                            if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")) {
                                GamemodeUtils.gamemodeswitcher(target.getPlayer(), GameMode.CREATIVE, "gmcm");
                            }
                            if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")) {
                                GamemodeUtils.gamemodeswitcher(target.getPlayer(), GameMode.SURVIVAL, "gmsm");
                            }
                            if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")) {
                                GamemodeUtils.gamemodeswitcher(target.getPlayer(), GameMode.ADVENTURE, "gmam");
                            }
                            if (args[0].equalsIgnoreCase("spec") || args[0].equalsIgnoreCase("spectator")) {
                                GamemodeUtils.gamemodeswitcher(target.getPlayer(), GameMode.SPECTATOR, "gmspecm");
                            }
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