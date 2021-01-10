package me.moonlight.bedwarssetup.commands;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.manager.SubCommandManager;
import me.moonlight.bedwarssetup.util.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static me.moonlight.bedwarssetup.util.MethodUtils.sendPlayerMessage;

/**
 * Main Bedwars Setup (/bs) Command
 * This command is the main command that is
 * used for other {@link AbstractSubCommand}
 */
public class BedwarsSetupCommand extends AbstractCommand {

    private final SubCommandManager subCommandManager;

    public BedwarsSetupCommand(Main main) {
        super(main, "bs", false);
        this.subCommandManager = main.getSubCommandManager();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // arguments as list
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        // check if the argument is empty, if so execute help command
        if(args.length == 0 || arguments.isEmpty()) {
            subCommandManager.executeCommand("help", sender, null);
            return;
        }
        String subCommandName = arguments.get(0);
        // remove the sub command from the arguments
        arguments.remove(0);
        // to check if the command exists
        AtomicBoolean commandFound = new AtomicBoolean(false);
        // loop through every subcommand
        subCommandManager.getSubCommands().stream()
                .filter(subCommand -> subCommand.getName().equalsIgnoreCase(subCommandName))
                .findFirst().ifPresent(subCommand -> {
                    subCommandManager.executeCommand(subCommand.getName(), sender, arguments.toArray(new String[0]));
                    commandFound.set(true);
                });
        // if command doesn't exist
        if(!(commandFound).get()) {
            sendPlayerMessage(sender, Lang.ERROR_PLAYER_COMMAND_DOESNT_EXIST);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // argument in list
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        List<String> finalTabComplete = new ArrayList<>();
        // if arg doesn't exist or doesn't match any command
        if( (args.length == 0) || (args.length == 1 && subCommandManager.getSubCommands().stream()
                        .noneMatch(abstractSubCommand -> abstractSubCommand.getName().equalsIgnoreCase(args[0]))) || arguments.isEmpty() ) {
            // add every sub command to the final tab complete list
            finalTabComplete.addAll(subCommandManager.getSubCommands().stream().map(AbstractSubCommand::getName).collect(Collectors.toList()));
        } else {
            // if the sub command exist, (this return the sub command's tab complete)
            arguments.remove(0);
            subCommandManager.getSubCommands().stream()
                    .filter(subCommand -> subCommand.getName().equals(args[0]))
                    .map(subCommand -> subCommand.tabComplete(sender, command, alias, arguments))
                    .forEach(finalTabComplete::addAll);
        }
        return finalTabComplete;
    }
}
