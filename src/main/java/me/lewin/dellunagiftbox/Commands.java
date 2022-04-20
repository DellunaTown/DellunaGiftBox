package me.lewin.dellunagiftbox;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (args.length == 0) {
            player.openInventory(InventoryGUI.getInventory((OfflinePlayer) player));
            return true;
        }

        if (player.isOp()) {
            switch (args[0]) {
                case "add":
                case "지급":
                    ItemStack item = player.getItemInHand();
                    for (String name : args) {
                        if (name.equals(args[0])) continue;
                        if (!PlayerData.getPlayerFile(Bukkit.getOfflinePlayer(name).getUniqueId().toString()).exists()) {
                            PlayerData.creatDataFile(Bukkit.getOfflinePlayer(name));
                        }
                        FileConfiguration config = PlayerData.getPlayerConfig(Bukkit.getOfflinePlayer(name).getUniqueId().toString());
                        ArrayList<ItemStack> list = (ArrayList<ItemStack>) config.get("item");
                        list.add(item);
                        config.set("item", list);
                        PlayerData.saveDataFile(config, PlayerData.getPlayerFile(Bukkit.getOfflinePlayer(name).getUniqueId().toString()));
                        player.sendMessage(name + "님께 선물 지급 완료");

                        if (Bukkit.getOfflinePlayer(name).isOnline()) {
                            ((Player) Bukkit.getOfflinePlayer(name)).sendMessage("§7[§a ! §7] §a서버로부터 선물이 도착했습니다. /gift 혹은 /선물함 으로 확인해보세요!");
                        }
                    }
                    break;
                case "open":
                case "열기":
                    player.openInventory(InventoryGUI.getInventory(Bukkit.getOfflinePlayer(args[1])));
                    break;
            }
        }
        return true;
    }
}
