package me.dartanman.rockpaperscissors;

import me.dartanman.rockpaperscissors.commands.RPSCommand;
import me.dartanman.rockpaperscissors.game.listener.RPSListener;
import me.dartanman.rockpaperscissors.game.manager.RPSManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class RockPaperScissors extends JavaPlugin
{

    private RPSManager gameManager;
    private static final boolean debugMode = true;

    public void onEnable()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();

        gameManager = new RPSManager();

        getCommand("rockpaperscissors").setExecutor(new RPSCommand(this));

        getServer().getPluginManager().registerEvents(new RPSListener(this), this);
    }

    public RPSManager getGameManager()
    {
        return gameManager;
    }

    public static void debug(String message)
    {
        if(debugMode)
        {
            Bukkit.broadcastMessage(ChatColor.AQUA + "DEBUG: " + ChatColor.RESET + message);
        }
    }

}
