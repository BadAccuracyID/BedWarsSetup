package me.moonlight.bedwarssetup.commands;

import me.moonlight.bedwarssetup.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

public class HelpSubCommand extends AbstractSubCommand {

    private final Main main;

    public HelpSubCommand(Main main) {
        super("help", "&aShow help menu", "" ,false);
        this.main = main;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        List<String> helpMessage = new ArrayList<>(Arrays.asList(
                EMPTY_LINE,
                "&8&L> &6BedWars Setup v" + main.getDescription().getVersion() + " &7- &5Setup Commands",
                EMPTY_LINE
        ));
        for(AbstractSubCommand subCommand : main.getSubCommandManager().getSubCommands()) {
            helpMessage.add("&6• &7/bs " + subCommand.getName() + " " + subCommand.getArguments() + " - &a" + subCommand.getDescription());
        }
        helpMessage.add(EMPTY_LINE);
        sendPlayerMessage(commandSender, helpMessage.toArray(new String[0]));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        return new ArrayList<>();
    }
}
