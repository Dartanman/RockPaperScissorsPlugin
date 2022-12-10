package me.dartanman.rockpaperscissors.game.abs;

import me.dartanman.rockpaperscissors.RockPaperScissors;
import me.dartanman.rockpaperscissors.game.RPSOption;
import me.dartanman.rockpaperscissors.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

import static me.dartanman.rockpaperscissors.utils.StringUtils.color;

public abstract class RPSGame
{

    protected RockPaperScissors plugin;

    protected final List<UUID> playerUUIDs;
    protected boolean hasStarted;

    protected Map<UUID, RPSOption> playedMap;

    protected RPSGame(UUID playerOneUUID)
    {
        playerUUIDs = new ArrayList<>();
        playerUUIDs.add(playerOneUUID);
        playedMap = new HashMap<>();

        plugin = JavaPlugin.getPlugin(RockPaperScissors.class);
    }

    public void play(UUID uuid, RPSOption option)
    {
        //RockPaperScissors.debug("PLAYED " + option.toString());
        playedMap.put(uuid, option);
        Player player = Bukkit.getPlayer(uuid);
        assert player != null;
        String message = plugin.getConfig().getString("Messages.Played").replace("<move>", option.toString().toLowerCase());
        player.sendMessage(color(message));
    }

    public boolean containsPlayerWithUUID(UUID uuid)
    {
        return playerUUIDs.contains(uuid);
    }

    public boolean hasStarted()
    {
        return hasStarted;
    }

    public void start()
    {
        hasStarted = true;
    }

    public abstract boolean allPlayed();

    public abstract void showResults();

}
