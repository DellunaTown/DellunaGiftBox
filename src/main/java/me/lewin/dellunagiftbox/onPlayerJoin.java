package me.lewin.dellunagiftbox;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {
    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (!(PlayerData.getPlayerFile(player.getUniqueId().toString()).canRead())){
            PlayerData.creatDataFile(player);
        }

        setReloadPlayerName(player, player.getUniqueId().toString());
    }

    private void setReloadPlayerName(Player player, String uuid)
    {
        FileConfiguration config = PlayerData.getPlayerConfig(uuid);
        config.set("name", player.getName());
        PlayerData.saveDataFile(config, PlayerData.getPlayerFile(uuid));
    }
}
