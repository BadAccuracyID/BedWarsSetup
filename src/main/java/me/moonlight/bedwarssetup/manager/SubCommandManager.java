package me.moonlight.bedwarssetup.manager;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.commands.*;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sub Command Manager - Manages sub commands
 */
public class SubCommandManager {

    // SubCommands List
    @Getter List<AbstractSubCommand> subCommands = new ArrayList<>();

    public SubCommandManager(Main main) {
        registerSubCommand(
                new HelpSubCommand(main),
                new DebugSubCommand(main),
                new GiveSubCommand(main),
                new SetTeamSubCommand(main),
                new MenuSubCommand(main)
        );
    }

    /**
     * register a sub command by
     * adding them to the sub command list
     * @param subCommand the subCommand to register
     */
    public void registerSubCommand(AbstractSubCommand... subCommand) {
        this.subCommands.addAll(Arrays.asList(subCommand));
    }

    /**
     * execute a sub command by their name
     * @param commandName the name of the sub commands
     * @param commandSender the sender of the command
     * @param args the arguments to pass
     */
    public void executeCommand(String commandName, CommandSender commandSender, String[] args) {
        for (AbstractSubCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(commandName)) {
                executeCommand(subCommand, commandSender, args);
            }
        }
    }

    /**
     * execute a sub command
     * @param command the sub command to execute
     * @param commandSender the sender of the command
     * @param args the arguments to pass
     */
    public void executeCommand(AbstractSubCommand command, CommandSender commandSender, String[] args) {
        command.execute(commandSender, args);
    }

    /**
     * find a sub command by their name
     * @param commandName the name of the sub command to find
     * @return the found sub command, null if not found
     */
    public AbstractSubCommand findCommand(String commandName) {
        return subCommands.stream().filter(subCommand -> subCommand.getName().equalsIgnoreCase(commandName)).findFirst().orElse(null);
    }

}
