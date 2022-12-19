package me.dartanman.rockpaperscissors.commands;

import me.dartanman.rockpaperscissors.RockPaperScissors;
import me.dartanman.rockpaperscissors.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPSCommand implements CommandExecutor
{

    private RockPaperScissors plugin;

    public RPSCommand(RockPaperScissors plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        switch (args.length)
        {
            case 0:
                MessageUtils.sendConfigMessage(sender, "Messages.Incorrect-Syntax");
                return true;
            case 1:
                if (args[0].equalsIgnoreCase("help"))
                {
                    MessageUtils.sendConfigListMessage(sender, "Messages.Help");
                    return true;
                }
                else if (args[0].equalsIgnoreCase("ai"))
                {
                    if(!(sender instanceof Player))
                    {
                        MessageUtils.sendConfigMessage(sender, "Messages.Must-Be-A-Player");
                        return true;
                    }
                    if(!sender.hasPermission("rps.play"))
                    {
                        MessageUtils.sendConfigMessage(sender, "Messages.Insufficient-Permissions");
                        return true;
                    }

                    plugin.getGameManager().createSinglePlayerGame((Player)sender).start();
                    return true;
                }
                else if (args[0].equalsIgnoreCase("reload"))
                {
                    if(!sender.hasPermission("rps.reload"))
                    {
                        MessageUtils.sendConfigMessage(sender, "Messages.Insufficient-Permissions");
                        return true;
                    }
                    plugin.reloadConfig();
                    MessageUtils.sendConfigMessage(sender, "Messages.Config-Reloaded");
                }
            default:
                MessageUtils.sendConfigMessage(sender, "Messages.Incorrect-Syntax");
                return true;
        }
    }
}
