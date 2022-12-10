package me.dartanman.rockpaperscissors.utils;

import me.dartanman.rockpaperscissors.game.RPSOption;
import static me.dartanman.rockpaperscissors.game.RPSOption.ROCK;
import static me.dartanman.rockpaperscissors.game.RPSOption.PAPER;
import static me.dartanman.rockpaperscissors.game.RPSOption.SCISSORS;

public class WinChecker
{

    private WinChecker(){}

    public static int doesFirstBeatSecond(RPSOption first, RPSOption second)
    {
        if(first == ROCK)
        {
            return second == ROCK ? 2 : second == SCISSORS ? 1 : 0;
        }
        else if (first == PAPER)
        {
            return second == PAPER ? 2 : second == ROCK ? 1 : 0;
        }
        else
        {
            return second == SCISSORS ? 2 : second == PAPER ? 1 : 0;
        }
    }

}
