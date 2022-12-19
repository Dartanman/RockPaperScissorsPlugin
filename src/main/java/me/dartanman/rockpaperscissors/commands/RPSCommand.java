package me.dartanman.rockpaperscissors.commands;

import me.dartanman.rockpaperscissors.RockPaperScissors;
import me.dartanman.rockpaperscissors.game.abs.RPSGame;
import me.dartanman.rockpaperscissors.game.abs.RPSGameMulti;
import me.dartanman.rockpaperscissors.utils.MessageUtils;
import org.bukkit.Bukkit;
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
                else
                {
                    if(!(sender instanceof Player player))
                    {
                        MessageUtils.sendConfigMessage(sender, "Messages.Must-Be-A-Player");
                        return true;
                    }

                    if(!sender.hasPermission("rps.play"))
                    {
                        MessageUtils.sendConfigMessage(sender, "Messages.Insufficient-Permissions");
                        return true;
                    }

                    String targetName = args[0];
                    Player target = Bukkit.getPlayer(targetName);
                    if(target == null)
                    {
                        MessageUtils.sendConfigMessage(sender, "Messages.Player-Offline");
                        return true;
                    }

                    RPSGame oppGame = plugin.getGameManager().getGame(target.getUniqueId());
                    if(oppGame != null)
                    {
                        MessageUtils.sendConfigMessage(sender, "Messages.Already-In-Game");
                        return true;
                    }

                    plugin.getGameManager().createMultiPlayerGame(player, target).start();

                }
            default:
                MessageUtils.sendConfigMessage(sender, "Messages.Incorrect-Syntax");
                return true;
        }
    }
}
