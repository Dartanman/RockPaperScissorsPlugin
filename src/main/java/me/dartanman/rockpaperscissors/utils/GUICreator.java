package me.dartanman.rockpaperscissors.utils;

import me.dartanman.rockpaperscissors.RockPaperScissors;
import me.dartanman.rockpaperscissors.game.RPSOption;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import static me.dartanman.rockpaperscissors.utils.StringUtils.color;

public class GUICreator
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

    private GUICreator()
    {
    }

    public static Inventory createGameGUI()
    {
        Inventory inv = Bukkit.createInventory(null, 27, color(getPlugin().getConfig().getString("Settings.GUI-Name")));

        Material rockMat = Material.valueOf(getPlugin().getConfig().getString("Settings.Rock-Item.Material"));
        Material paperMat = Material.valueOf(getPlugin().getConfig().getString("Settings.Paper-Item.Material"));
        Material scissorsMat = Material.valueOf(getPlugin().getConfig().getString("Settings.Scissors-Item.Material"));
        Material fillerMat = Material.valueOf(getPlugin().getConfig().getString("Settings.Filler-Item.Material"));

        ItemStack rockItem = new ItemStack(rockMat);
        ItemStack paperItem = new ItemStack(paperMat);
        ItemStack scissorsItem = new ItemStack(scissorsMat);
        ItemStack fillerItem = new ItemStack(fillerMat);

        ItemMeta rockMeta = rockItem.getItemMeta();
        ItemMeta paperMeta = paperItem.getItemMeta();
        ItemMeta scissorsMeta = scissorsItem.getItemMeta();

        String rockName = color(getPlugin().getConfig().getString("Settings.Rock-Item.Display-Name"));
        String paperName = color(getPlugin().getConfig().getString("Settings.Paper-Item.Display-Name"));
        String scissorsName = color(getPlugin().getConfig().getString("Settings.Scissors-Item.Display-Name"));
        rockMeta.setDisplayName(rockName);
        paperMeta.setDisplayName(paperName);
        scissorsMeta.setDisplayName(scissorsName);

        rockItem.setItemMeta(rockMeta);
        paperItem.setItemMeta(paperMeta);
        scissorsItem.setItemMeta(scissorsMeta);

        for (int i = 0; i < inv.getSize(); i++)
        {
            if (i == 11)
            {
                inv.setItem(i, rockItem);
            }
            else if (i == 13)
            {
                inv.setItem(i, paperItem);
            }
            else if (i == 15)
            {
                inv.setItem(i, scissorsItem);
            }
            else
            {
                inv.setItem(i, fillerItem);
            }
        }

        return inv;
    }

}
