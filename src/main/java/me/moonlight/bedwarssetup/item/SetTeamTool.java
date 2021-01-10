package me.moonlight.bedwarssetup.item;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.SetTeamSubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.moonlight.bedwarssetup.util.MethodUtils.color;

/**
 * Set Team Tool
 *
 * Setup item that set the current setup team name
 * @see SetTeamSubCommand
 */
public class SetTeamTool extends AbstractItem {

    @Getter List<UUID> playersInSession = new ArrayList<>();

    public SetTeamTool(Main main) {
        super(main, "SET_TEAM_TOOL", Material.COMPASS, 1, true, false,
                "&dSet Team Tool", true,
                "&6Set Setup Team &e&lRIGHT CLICK",
                "&7Set the setup team name"
        );
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        // check if the player is already in a set team name session
        if(playersInSession.contains(player.getUniqueId())) {
            player.sendTitle(color("&6&lType team name"), color("&ein the chat!"));
            return;
        }
        // if not, add them to a set team name session
        playersInSession.add(player.getUniqueId());
        // send title
        player.sendTitle(color("&6&lType team name"), color("&ein the chat!"));
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {
        // nothing here...
    }
}
