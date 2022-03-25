package me.lewin.dellunagiftbox;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class onPlayerUse implements Listener {
    @EventHandler
    private void onPlayerUse(PlayerInteractEvent event){
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        if (!item.hasItemMeta()) return;

        if (item.getItemMeta().hasLore()){
            List <String> lore = item.getItemMeta().getLore();
            for (int i = 0; i < lore.size(); i++){
                if (lore.get(i).contains("우클릭으로 가방을 열 수 있다."))
                    player.openInventory(new InventoryGUI(player).getInventory());
            }
        }

        return;
    }
}
