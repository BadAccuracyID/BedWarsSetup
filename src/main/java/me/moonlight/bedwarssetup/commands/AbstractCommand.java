package me.moonlight.bedwarssetup.commands;

import lombok.Getter;
import me.moonlight.bedwarssetup.Main;
import me.moonlight.bedwarssetup.util.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

/**
 * Template for commands, which extends {@link Command}
 * and includes the {@link TabExecutor} for the command
 */
public abstract class AbstractCommand implements CommandExecutor, TabExecutor {

    // determine if the command is player only or not
    private final boolean playerOnly;
    // command name
    @Getter private final String name;

    public AbstractCommand(Main main, String name, boolean playerOnly) {
        super();
        this.playerOnly = playerOnly;
        this.name = name;

        // check if the command is registered in plugin.yml or not
        try {
            main.getCommand(name).setExecutor(this);
            main.getCommand(name).setTabCompleter(this);
        } catch (NullPointerException e) {
            error("Command " + name + " is not registered in plugin.yml!");
            e.printStackTrace();
        }
    }

    /**
     * Called when a user execute this command
     * @param sender the sender of the command
     * @param args the arguments that the user specify
     */
    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(playerOnly) {
            if ((sender instanceof Player)) execute(sender, args);
            else sendPlayerMessage(sender, Lang.ERROR_PLAYER_ONLY_COMMAND);
            return true;
        }
        execute(sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return tabComplete(sender, command, alias, args);
    }

    /**
     * called when a player use
     * tab complete on the command
     * @return the {@link List} of suggestion
     */
    public abstract List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args);
}
