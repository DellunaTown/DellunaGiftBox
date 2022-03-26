package me.lewin.dellunagiftbox;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryGUI {
    public static Inventory getInventory(OfflinePlayer player){
        Inventory inv = Bukkit.getServer().createInventory(null, 27, "§x§0§0§b§3§b§6" + player.getName() + "§x§0§0§b§3§b§6의 선물함");
        FileConfiguration config = PlayerData.getPlayerConfig(player.getUniqueId().toString());
        int count = 0;
        for (ItemStack item : (ArrayList<ItemStack>) config.get("item")){
            inv.setItem(count, item);
            count++;
        }
        return inv;
    }
}
