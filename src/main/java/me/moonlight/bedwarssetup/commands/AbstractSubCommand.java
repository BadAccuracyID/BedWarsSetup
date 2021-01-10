package me.moonlight.bedwarssetup.commands;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

import static me.moonlight.bedwarssetup.util.MethodUtils.color;

/**
 * Sub command template.
 */
public abstract class AbstractSubCommand {

    @Getter public String name;
    @Getter public boolean playerOnly;
    @Getter public String description;
    @Getter public String arguments;

    public AbstractSubCommand(String name, String description, String arguments, boolean playerOnly) {
        this.name = name;
        this.arguments = arguments == null ? "" : arguments;
        this.description = color(description);
        this.playerOnly = playerOnly;
    }

    // called when the subcommand is executed
    public abstract void execute(CommandSender commandSender, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args);

}
