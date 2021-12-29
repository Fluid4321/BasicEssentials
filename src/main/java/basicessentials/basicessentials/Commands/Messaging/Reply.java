package basicessentials.basicessentials.Commands.Messaging;

import basicessentials.basicessentials.BasicEssentials;
import basicessentials.basicessentials.Utils.ChatUtils;
import basicessentials.basicessentials.Utils.PermUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Reply implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (PermUtils.PermChecker(player, "msgp")) {
                if (args.length < 1) {
                    ChatUtils.Args(player);
                } else if (BasicEssentials.recentmessage.containsKey(player)) {
                    if (BasicEssentials.recentmessage.get(player) != null) {
                        OfflinePlayer target = BasicEssentials.recentmessage.get(player);
                        String message = "";
                        for (int i = 0; i < args.length;  i++) {
                            message += args[i] + " ";
                        }
                        message = message.trim();

                        ChatUtils.Chat(Objects.requireNonNull(target.getPlayer()), BasicEssentials.getInstance().getConfig().getString("msgrecieve").replace("{player}", player.getDisplayName()).replace("{recievemessage}", message));
                    }

                } else {
                    Message.Message(args, player);
                }
            }
        }
        return false;
    }
}