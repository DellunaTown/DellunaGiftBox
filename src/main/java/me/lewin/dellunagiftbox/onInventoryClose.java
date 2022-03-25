package me.lewin.dellunagiftbox;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class onInventoryClose implements Listener {
    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        if (event.getView().getTitle().contains("§x§0§0§b§3§b§6의 가방")) {
            FileConfiguration config = PlayerData.getPlayerConfig(player.getUniqueId().toString());
            int i = 0;
            for (ItemStack item : event.getInventory().getContents()){
                String num = Integer.toString(i);
                if (item != null && item.getType() == Material.FLINT){
                    if (item.getItemMeta().hasCustomModelData()){
                        if (item.getItemMeta().getCustomModelData() == 1079){
                            if (player.getInventory().firstEmpty() == -1){
                                player.sendMessage("가방을 조심히 다뤄주세요...");
                                player.getWorld().dropItem(player.getLocation(), item);
                                config.set(num, null);
                                i++;
                                continue;
                            }
                            player.getInventory().addItem(item);
                            config.set(num, null);
                            i++;
                            continue;
                        }
                    }
                }
                config.set(num, item);
                i++;
            }
            PlayerData.saveDataFile(config, PlayerData.getPlayerFile(player.getUniqueId().toString()));
        }
    }
}
