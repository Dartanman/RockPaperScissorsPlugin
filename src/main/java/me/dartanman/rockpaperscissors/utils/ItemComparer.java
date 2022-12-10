package me.dartanman.rockpaperscissors.utils;

import me.dartanman.rockpaperscissors.RockPaperScissors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static me.dartanman.rockpaperscissors.utils.StringUtils.color;

public class ItemComparer
{

    private static RockPaperScissors plugin;

    private static RockPaperScissors getPlugin()
    {
        if (plugin == null)
        {
            plugin = JavaPlugin.getPlugin(RockPaperScissors.class);
        }
        return plugin;
    }

    private ItemComparer(){}

    public static boolean isRock(ItemStack item)
    {
        if(item.hasItemMeta())
        {
            if(item.getItemMeta().hasDisplayName())
            {
                if(item.getType().equals(Material.valueOf(getPlugin().getConfig().getString("Settings.Rock-Item.Material"))))
                {
                    if(item.getItemMeta().getDisplayName().equals(color(getPlugin().getConfig().getString("Settings.Rock-Item.Display-Name"))))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isPaper(ItemStack item)
    {
        if(item.hasItemMeta())
        {
            if(item.getItemMeta().hasDisplayName())
            {
                if(item.getType().equals(Material.valueOf(getPlugin().getConfig().getString("Settings.Paper-Item.Material"))))
                {
                    if(item.getItemMeta().getDisplayName().equals(color(getPlugin().getConfig().getString("Settings.Paper-Item.Display-Name"))))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isScissors(ItemStack item)
    {
        if(item.hasItemMeta())
        {
            if(item.getItemMeta().hasDisplayName())
            {
                if(item.getType().equals(Material.valueOf(getPlugin().getConfig().getString("Settings.Scissors-Item.Material"))))
                {
                    if(item.getItemMeta().getDisplayName().equals(color(getPlugin().getConfig().getString("Settings.Scissors-Item.Display-Name"))))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
