package basicessentials.basicessentials.Commands.Messaging;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Message implements CommandExecutor {

    public final BasicEssentials basicEssentials;

    public Message(BasicEssentials basicEssentials) {
        this.basicEssentials = basicEssentials;
    }


    public static void Message(String[] args, Player player) {

        if (Bukkit.getServer().getOfflinePlayer(args[0]).isOnline()) {
            OfflinePlayer target = (OfflinePlayer) Bukkit.getOfflinePlayer(args[0]);
            String message = "";
            for (int i = 1; i < args.length;  i++) {
                message += args[i] + " ";
            }
            message = message.trim();
            BasicEssentials.recentmessage.put(player, target);
            ChatUtils.Chat(Objects.requireNonNull(target.getPlayer()), BasicEssentials.getInstance().getConfig().getString("msgrecieve").replace("{player}", player.getDisplayName()).replace("{recievemessage}", message)); // I will need to shorten this haha very long
        } else {
            ChatUtils.NotOnline(player);
        }
    }

    // PlayerFrom PlayerSentTo

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (PermUtils.PermChecker(player, "msgp")) {
                if (args.length < 1) {
                    ChatUtils.Args(player);
                } Message(args, player);
            }
        }
        return false;
    }
}
