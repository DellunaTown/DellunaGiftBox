package me.lewin.dellunagiftbox;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGUI {
    Player player;

    public InventoryGUI(Player player) {
        this.player = player;
    }

    public Inventory getInventory(){
        Inventory inv = Bukkit.getServer().createInventory(null, 9, "§x§0§0§b§3§b§6" + player.getName() + "§x§0§0§b§3§b§6의 가방");
        FileConfiguration config = PlayerData.getPlayerConfig(player.getUniqueId().toString());
        for (int i = 0; i < 9; i++){
            String num = Integer.toString(i);
            ItemStack item = (ItemStack) config.get(num);
            inv.setItem(i, item);
        }
        return inv;
    }
}
