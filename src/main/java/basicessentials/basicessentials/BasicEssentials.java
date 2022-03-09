package basicessentials.basicessentials;

import basicessentials.basicessentials.Ranks.SubCommands.Rankinfo;
import basicessentials.basicessentials.Utils.SQLUtils;
import basicessentials.basicessentials.Commands.Gamemode.Gamemode;
import basicessentials.basicessentials.Commands.Messaging.Message;
import basicessentials.basicessentials.Commands.Messaging.Reply;
import basicessentials.basicessentials.Commands.Misc.*;
import basicessentials.basicessentials.Commands.Misc.Spawn.SetSpawn;
import basicessentials.basicessentials.Commands.Misc.Spawn.Spawn;
import basicessentials.basicessentials.Listeners.OnChat;
import basicessentials.basicessentials.Listeners.OnJoin;
import basicessentials.basicessentials.Listeners.OnLeave;
import basicessentials.basicessentials.Ranks.Rank;
import basicessentials.basicessentials.Ranks.SubCommands.SetRank;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class BasicEssentials extends JavaPlugin {
    // Config stuff
    FileConfiguration config = getConfig();

    // Modular Stuff
    private static BasicEssentials instance;
    private static SimpleCommandMap scm;
    private SimplePluginManager spm;

    // Array Hoo
    public static ArrayList<Player> afk = new ArrayList<Player>(); // We store whether the player is AFK in the array list
    public static HashMap<Player, OfflinePlayer> recentmessage = new HashMap<>(); // Storing Replys and Messages

    @Override
    public void onEnable() {
        instance = this;

        config.options().copyDefaults(true);
        saveDefaultConfig();

        SQLUtils.InitialiseSQL();

        getServer().getPluginManager().registerEvents(new OnJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnLeave(this), this);
        getServer().getPluginManager().registerEvents(new OnChat(), this);

        getCommand("rank").setExecutor(new Rank());
        getCommand("setrank").setExecutor(new SetRank());
        getCommand("gamemode").setExecutor(new Gamemode(this));
        getCommand("fly").setExecutor(new Fly());
        getCommand("heal").setExecutor(new Heal());
        getCommand("kill").setExecutor(new Kill(this));
        getCommand("afk").setExecutor(new Afk());
        getCommand("fun").setExecutor(new Fun(this));
        getCommand("message").setExecutor(new Message(this));
        getCommand("reply").setExecutor(new Reply());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("rankinfo").setExecutor(new Rankinfo());

        System.out.println("----------------------");
        System.out.println("LIGHT ESSENTIALS");
        System.out.println(" ");
        System.out.println("MySQL: Connected");
        System.out.println("Status: Enabled");
        System.out.println("----------------------");
    }

    public static BasicEssentials getInstance() {
        return instance;
    }



    @Override
    public void onDisable() {
        System.out.println("----------------------");
        System.out.println("LIGHT ESSENTIALS");
        System.out.println("Status: Disabling");
        System.out.println("----------------------");
        instance = null;
    }
}
