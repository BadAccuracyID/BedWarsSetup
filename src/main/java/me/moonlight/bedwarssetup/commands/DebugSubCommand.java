package me.moonlight.bedwarssetup.commands;

import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.util.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

// debug command, to turn on/off plugin debug system
public class DebugSubCommand extends AbstractSubCommand {

    private Main main;

    public DebugSubCommand(Main main) {
        super("debug", "Turn off/on debug mode", "<true | false>", false);
        this.main = main;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        try {
            // check if the user type the correct argument or not
            if ((args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("false"))) {
                main.setDebug(Boolean.parseBoolean(args[0]));
                debug("Debug has been set to " + args[0]);
                sender.sendMessage(color("&aDebug has been set to " + args[0]));
            } else {
                sendPlayerMessage(sender, Lang.ERROR_PLAYER_WRONG_ARGUMENT, this.getName() + " " + this.getArguments());
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            sendPlayerMessage(sender, Lang.ERROR_PLAYER_NOT_ENOUGH_ARGUMENT);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        return (!(Arrays.toString(args.toArray()).equalsIgnoreCase("[]"))) ? new ArrayList<>() : Arrays.asList("true", "false");

    }
}