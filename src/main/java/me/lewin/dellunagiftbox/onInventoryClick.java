package me.lewin.dellunagiftbox;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class onInventoryClick implements Listener {
    @EventHandler
    private void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().contains("§x§0§0§b§3§b§6의 선물함")) {
            if (event.getClickedInventory() == null) return;
            if (event.getClickedInventory() == event.getView().getBottomInventory()) return;
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);

            player.getInventory().addItem(event.getCurrentItem());
            String name = event.getView().getTitle().replace("§x§0§0§b§3§b§6의 선물함", "").replace("§x§0§0§b§3§b§6", "");

            FileConfiguration config = PlayerData.getPlayerConfig(Bukkit.getOfflinePlayer(name).getUniqueId().toString());
            ArrayList<ItemStack> list = (ArrayList<ItemStack>) config.get("item");
            if (list.size() > event.getSlot()) {
                list.remove(event.getSlot());
            }
            config.set("item", list);
            PlayerData.saveDataFile(config, PlayerData.getPlayerFile(Bukkit.getOfflinePlayer(name).getUniqueId().toString()));
            player.openInventory(InventoryGUI.getInventory(Bukkit.getOfflinePlayer(name)));
        }
    }
}
