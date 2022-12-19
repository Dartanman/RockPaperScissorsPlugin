package me.dartanman.rockpaperscissors.game.abs;

import me.dartanman.rockpaperscissors.game.RPSOption;
import me.dartanman.rockpaperscissors.utils.GUICreator;
import me.dartanman.rockpaperscissors.utils.MessageUtils;
import me.dartanman.rockpaperscissors.utils.WinChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

import static me.dartanman.rockpaperscissors.utils.StringUtils.color;

public class RPSGameMulti extends RPSGame
{

    public RPSGameMulti(UUID playerOneUUID, UUID playerTwoUUID)
    {
        super(playerOneUUID);
        playerUUIDs.add(playerTwoUUID);
    }

    @Override
    public void start()
    {
        super.start();
        Player playerOne = Bukkit.getPlayer(playerUUIDs.get(0));
        Player playerTwo = Bukkit.getPlayer(playerUUIDs.get(1));

        assert playerOne != null;
        assert playerTwo != null;

        Inventory rpsGUI = GUICreator.createGameGUI();

        playerOne.openInventory(rpsGUI);
        playerTwo.openInventory(rpsGUI);
    }

    @Override
    public void play(UUID uuid, RPSOption option)
    {
        super.play(uuid, option);
        if(allPlayed())
        {
            showResults();
        }
    }

    @Override
    public boolean allPlayed()
    {
        return playedMap.containsKey(playerUUIDs.get(0)) && playedMap.containsKey(playerUUIDs.get(1));
    }

    @Override
    public void showResults()
    {
        Player playerOne = Bukkit.getPlayer(playerUUIDs.get(0));
        Player playerTwo = Bukkit.getPlayer(playerUUIDs.get(1));

        assert playerOne != null;
        assert playerTwo != null;

        RPSOption onePlay = playedMap.get(playerOne.getUniqueId());
        RPSOption twoPlay = playedMap.get(playerTwo.getUniqueId());

        String oneMessage = plugin.getConfig().getString("Messages.Result").replace("<move>", twoPlay.toString().toLowerCase());
        playerOne.sendMessage(color(oneMessage));

        String twoMessage = plugin.getConfig().getString("Messages.Result").replace("<move>", onePlay.toString().toLowerCase());
        playerTwo.sendMessage(color(twoMessage));

        int result = WinChecker.doesFirstBeatSecond(onePlay, twoPlay);
        switch (result)
        {
            case 0:
                MessageUtils.sendConfigMessage(playerOne, "Messages.Lose");
                MessageUtils.sendConfigMessage(playerTwo, "Messages.Win");
                break;
            case 1:
                MessageUtils.sendConfigMessage(playerOne, "Messages.Win");
                MessageUtils.sendConfigMessage(playerTwo, "Messages.Lose");
                break;
            default:
                MessageUtils.sendConfigMessage(playerOne, "Messages.Tie");
                MessageUtils.sendConfigMessage(playerTwo, "Messages.Tie");
                break;
        }

        getManager().removeGame(this);
    }
}
