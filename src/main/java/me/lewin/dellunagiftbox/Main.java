package me.lewin.dellunagiftbox;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Plugin plugin = JavaPlugin.getPlugin(Main.class);

        this.getCommand("bp").setExecutor(new Commands());

        Bukkit.getPluginManager().registerEvents(new onPlayerUse(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryClose(), this);

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
    }
}
