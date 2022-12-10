package me.dartanman.rockpaperscissors.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.dartanman.rockpaperscissors.RockPaperScissors;

import java.util.List;

import static me.dartanman.rockpaperscissors.utils.StringUtils.color;

public class MessageUtils
{

    private static RockPaperScissors plugin;

    private static RockPaperScissors getPlugin()
    {
        if(plugin == null)
        {
            plugin = JavaPlugin.getPlugin(RockPaperScissors.class);
        }
        return plugin;
    }

    private MessageUtils(){}

    public static void sendConfigListMessage(CommandSender sender, String configPath)
    {
        List<String> messageList = getPlugin().getConfig().getStringList(configPath);
        for(String message : messageList)
        {
            sender.sendMessage(color(message));
        }
    }

    public static void sendConfigMessage(CommandSender sender, String configPath)
    {
        String message = getPlugin().getConfig().getString(configPath);
        sender.sendMessage(color(message));
    }

}
