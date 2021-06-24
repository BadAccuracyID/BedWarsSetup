package me.moonlight.bedwarssetup.commands;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.util.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;

/**
 * Set the current setup team, which is
 * used in the setup items
 */
public class SetTeamSubCommand extends AbstractSubCommand {

    @Getter public Map<UUID, String> setupTeams = new HashMap<>();

    public SetTeamSubCommand(Main main) {
        super("setteam", "Set your current setup team", "<teamName>", true);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        try {
            // put the player to the setup map
            this.setupTeams.put(((Player) commandSender).getUniqueId(), args[0]);
            ((Player) commandSender).performCommand("bw createTeam " + args[0] + " " + args[0]);
            sendPlayerMessage(commandSender, "&aCurrent team's name has been set to: " + args[0]);
        } catch(ArrayIndexOutOfBoundsException e) {
            // if player doesn't input any team name
            sendPlayerMessage(commandSender, Lang.ERROR_PLAYER_NOT_ENOUGH_ARGUMENT, "/" + (this.getName() + " " + this.getArguments()));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        return new ArrayList<>();
    }
}
