package me.dartanman.rockpaperscissors.utils;

import org.bukkit.ChatColor;

public class StringUtils
{

    private StringUtils(){}

    public static String color(String toColor)
    {
        return ChatColor.translateAlternateColorCodes('&', toColor);
    }
}
