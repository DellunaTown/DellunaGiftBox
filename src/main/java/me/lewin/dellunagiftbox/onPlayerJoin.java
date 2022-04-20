package me.lewin.dellunagiftbox;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class onPlayerJoin implements Listener {
    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (!(PlayerData.getPlayerFile(player.getUniqueId().toString()).canRead())){
            PlayerData.creatDataFile((OfflinePlayer) player);
        }

        Plugin plugin = JavaPlugin.getPlugin(Main.class);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            FileConfiguration config = PlayerData.getPlayerConfig(player.getUniqueId().toString());
            if (((ArrayList<ItemStack>)config.get("item")).size() > 0){
                player.sendMessage("§7[§a ! §7] §a서버로부터 선물이 도착했습니다. /gift 혹은 /선물함 으로 확인해보세요!");
            }
        }, 60L);

    }
}
