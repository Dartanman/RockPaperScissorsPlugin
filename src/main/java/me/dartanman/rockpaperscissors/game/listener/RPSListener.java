package me.dartanman.rockpaperscissors.game.listener;

import me.dartanman.rockpaperscissors.RockPaperScissors;
import me.dartanman.rockpaperscissors.game.RPSOption;
import me.dartanman.rockpaperscissors.game.abs.RPSGame;
import me.dartanman.rockpaperscissors.utils.ItemComparer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

import static me.dartanman.rockpaperscissors.utils.StringUtils.color;

public class RPSListener implements Listener
{

    private RockPaperScissors plugin;

    public RPSListener(RockPaperScissors plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        if(event.getWhoClicked() instanceof Player player)
        {
            ItemStack item = event.getCurrentItem();
            InventoryView view = event.getView();

            if(view.getTitle().equalsIgnoreCase(color(plugin.getConfig().getString("Settings.GUI-Name"))))
            {
                event.setCancelled(true);
                UUID uuid = player.getUniqueId();
                RPSGame game = plugin.getGameManager().getGame(uuid);
                assert game != null;
                assert item != null && item.getType() != Material.AIR;
                if(ItemComparer.isRock(item))
                {
                    game.play(uuid, RPSOption.ROCK);
                }
                else if(ItemComparer.isPaper(item))
                {
                    game.play(uuid, RPSOption.PAPER);
                }
                else if(ItemComparer.isScissors(item))
                {
                    game.play(uuid, RPSOption.SCISSORS);
                }
                player.closeInventory();
            }
        }
    }

}
