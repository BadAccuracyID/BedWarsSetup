package me.moonlight.bedwarssetup.commands;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.util.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;

public class SetTeamSubCommand extends AbstractSubCommand {

    private final Main main;
    @Getter public Map<UUID, String> setupTeams = new HashMap<>();

    public SetTeamSubCommand(Main main) {
        super("setteam", "Set your current setup team", "<teamName>", true);
        this.main = main;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        try {
            this.setupTeams.put(((Player) commandSender).getUniqueId(), args[0]);
            sendPlayerMessage(commandSender, "&aCurrent team's name has been set to: " + args[0]);
        } catch(ArrayIndexOutOfBoundsException e) {
            sendPlayerMessage(commandSender, Lang.ERROR_PLAYER_NOT_ENOUGH_ARGUMENT, "/" + (this.getName() + " " + this.getArguments()));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        return new ArrayList<>();
    }
}
