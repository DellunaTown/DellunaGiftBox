package me.lewin.dellunagiftbox;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    public static void creatDataFile(Player player){
        String uuid = player.getUniqueId().toString();
        FileConfiguration config = getPlayerConfig(uuid);
        config.set("0", null);
        config.set("1", null);
        config.set("2", null);
        config.set("3", null);
        config.set("4", null);
        config.set("5", null);
        config.set("6", null);
        config.set("7", null);
        config.set("8", null);
        saveDataFile(config, getPlayerFile(uuid));
    }

    public static final Plugin PLUGIN = JavaPlugin.getPlugin(Main.class);
    public static File getPlayerFile(String uuid) {
        return new File(PLUGIN.getDataFolder() + "\\InventoryData", uuid + ".dat");
    }

    public static FileConfiguration getPlayerConfig(String uuid) {
        return YamlConfiguration.loadConfiguration(getPlayerFile(uuid));
    }

    public static void saveDataFile(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("§cFile I/O Error!!");
        }
    }
}
