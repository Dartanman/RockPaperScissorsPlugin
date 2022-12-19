package me.dartanman.rockpaperscissors.game.abs;

import me.dartanman.rockpaperscissors.game.RPSOption;
import me.dartanman.rockpaperscissors.utils.GUICreator;
import me.dartanman.rockpaperscissors.utils.MessageUtils;
import me.dartanman.rockpaperscissors.utils.WinChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Random;
import java.util.UUID;

import static me.dartanman.rockpaperscissors.utils.StringUtils.color;

public class RPSGameSingle extends RPSGame
{

    public RPSGameSingle(UUID playerOneUUID)
    {
        super(playerOneUUID);
    }

    @Override
    public void play(UUID uuid, RPSOption option)
    {
        super.play(uuid, option);
        showResults();
    }

    @Override
    public void start()
    {
        super.start();
        Player player = Bukkit.getPlayer(playerUUIDs.get(0));
        assert player != null;
        Inventory rpsGUI = GUICreator.createGameGUI();
        player.openInventory(rpsGUI);
    }

    @Override
    public boolean allPlayed()
    {
        return playedMap.containsKey(playerUUIDs.get(0));
    }

    @Override
    public void showResults()
    {
        Random random = new Random();
        double d = random.nextDouble();
        RPSOption aiOption;
        if(d >= 0.67)
        {
            aiOption = RPSOption.ROCK;
        }
        else if (d >= 0.33)
        {
            aiOption = RPSOption.PAPER;
        }
        else
        {
            aiOption = RPSOption.SCISSORS;
        }

        Player player = Bukkit.getPlayer(playerUUIDs.get(0));
        assert player != null;

        String message = plugin.getConfig().getString("Messages.Result").replace("<move>", aiOption.toString().toLowerCase());
        player.sendMessage(color(message));

        RPSOption playerOption = playedMap.get(player.getUniqueId());

        int result = WinChecker.doesFirstBeatSecond(playerOption, aiOption);
        switch (result)
        {
            case 0 -> MessageUtils.sendConfigMessage(player, "Messages.Lose");
            case 1 -> MessageUtils.sendConfigMessage(player, "Messages.Win");
            default -> MessageUtils.sendConfigMessage(player, "Messages.Tie");
        }

        getManager().removeGame(this);
    }

}
