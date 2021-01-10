package me.moonlight.bedwarssetup.listener;

import com.andrei1058.bedwars.api.events.server.SetupSessionCloseEvent;
import com.andrei1058.bedwars.api.events.server.SetupSessionStartEvent;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.SetTeamSubCommand;
import me.moonlight.bedwarssetup.item.SetTeamTool;
import me.moonlight.bedwarssetup.manager.ItemManager;
import me.moonlight.bedwarssetup.manager.SubCommandManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.UUID;

// On setup start or stop event listener
public class SetupSessionListener implements Listener {

    private final ItemManager itemManager;
    private final SubCommandManager subCommandManager;

    public SetupSessionListener(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.itemManager = main.getItemManager();
        this.subCommandManager = main.getSubCommandManager();
    }

    @EventHandler
    public void onSetup(SetupSessionStartEvent event) {
        Player player = event.getSetupSession().getPlayer();
        // give setup items to the player
        itemManager.giveItem("SETUP_DIAMOND", player);
        // the next items should be in chronological order
        int i = 2;
        for (String key : itemManager.getItemMap().keySet()) {
            itemManager.giveItem(key, player, i);
            i++;
        }
    }

    @EventHandler
    public void onStopSetup(SetupSessionCloseEvent event) {
        Player player = event.getSetupSession().getPlayer();
        // check if player has set the team name
        Map<UUID, String> team = ((SetTeamSubCommand) subCommandManager.findCommand("setteam")).getSetupTeams();
        // if yes remove it
        if(team.get(player.getUniqueId()) != null) team.remove(player.getUniqueId());
        // remove player from set team sessions
        ((SetTeamTool) itemManager.getItem("SET_TEAM_TOOL")).getPlayersInSession().remove(player.getUniqueId());
    }
}
