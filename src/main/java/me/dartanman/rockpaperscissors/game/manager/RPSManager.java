package me.dartanman.rockpaperscissors.game.manager;

import me.dartanman.rockpaperscissors.game.abs.RPSGame;
import me.dartanman.rockpaperscissors.game.abs.RPSGameMulti;
import me.dartanman.rockpaperscissors.game.abs.RPSGameSingle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RPSManager
{

    private List<RPSGame> gameList;

    public RPSManager()
    {
        gameList = new ArrayList<>();
    }

    public List<RPSGame> getGameList()
    {
        return gameList;
    }

    public void addGame(RPSGame game)
    {
        gameList.add(game);
    }

    public void removeGame(RPSGame game)
    {
        gameList.remove(game);
    }

    public RPSGame createSinglePlayerGame(Player player)
    {
        UUID uuid = player.getUniqueId();
        RPSGameSingle game = new RPSGameSingle(uuid);
        addGame(game);
        return game;
    }

    public RPSGame createMultiPlayerGame(Player playerOne, Player playerTwo)
    {
        UUID oneUUID = playerOne.getUniqueId();
        UUID twoUUID = playerTwo.getUniqueId();

        RPSGameMulti game = new RPSGameMulti(oneUUID, twoUUID);
        addGame(game);
        return game;
    }

    public RPSGame getGame(UUID uuid)
    {
        for(RPSGame game : gameList)
        {
            if(game.containsPlayerWithUUID(uuid))
            {
                return game;
            }
        }
        return null;
    }

    public RPSGame getGame(Player player)
    {
        return getGame(player.getUniqueId());
    }


}
