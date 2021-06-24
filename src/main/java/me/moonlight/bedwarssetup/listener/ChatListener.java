package me.moonlight.bedwarssetup.listener;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.item.SetTeamTool;
import me.moonlight.bedwarssetup.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.UUID;

// chat listener for the set team session
public class ChatListener implements Listener {

    private final Main main;
    private final ItemManager itemManager;

    public ChatListener(Main main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
        this.itemManager = main.getItemManager();
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        // check if the player is in a set team session
        List<UUID> playerInSession = ((SetTeamTool) itemManager.getItem("SET_TEAM_TOOL")).getPlayersInSession();
        Player player = event.getPlayer();
        if(playerInSession.contains(player.getUniqueId())) {
            // if yes then set the team to the message content
            event.setCancelled(true);
            Bukkit.getScheduler().runTask(main, () -> player.performCommand("bs setteam " + event.getMessage()));
            playerInSession.remove(player.getUniqueId());
        }
    }

}
