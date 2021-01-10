package me.moonlight.bedwarssetup.commands;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.util.MethodUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.moonlight.bedwarssetup.util.MethodUtils.EMPTY_LINE;

/**
 * Help sub command, containing all
 * of the subcommands, their name, their usage,
 * their arguments, description, etc..
 */
public class HelpSubCommand extends AbstractSubCommand {

    private final Main main;

    public HelpSubCommand(Main main) {
        super("help", "&aShow help menu", "" ,false);
        this.main = main;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        // help message to send
        List<String> helpMessage = new ArrayList<>(Arrays.asList(
                EMPTY_LINE,
                "&8&L> &6BedWars Setup v" + main.getDescription().getVersion() + " &7- &5Setup Commands",
                EMPTY_LINE
        ));
        // loop through all of the subcommands, adding them to the help message
        main.getSubCommandManager().getSubCommands().stream()
                .map(subCommand -> "&6• &7/bs " + subCommand.getName() + " " + subCommand.getArguments() + " - &a" + subCommand.getDescription())
                .forEach(helpMessage::add);
        helpMessage.add(EMPTY_LINE);
        // send the message
        helpMessage.stream().map(MethodUtils::color).forEach(commandSender::sendMessage);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        // help command doesn't have arguments, so return an empty list
        return new ArrayList<>();
    }
}
