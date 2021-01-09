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

public class GiveSubCommand extends AbstractSubCommand {

    private final Main main;

    public GiveSubCommand(Main main) {
        super("giveitem", "Give an item ", "<Item ID>", true);
        this.main = main;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        debug("Executing Give Sub Command");
        try {
            debug("Executing Give Sub Command");
            if(main.getItemManager().getItem(args[0]) == null) {
                debug("The item is id in the argument is invalid!");
                sender.sendMessage(color("&cInvalid item name!"));
                return;
            }
            debug("the item id is valid.. giving the player item..");
            main.getItemManager().giveItem(main.getItemManager().getItem(args[0]), (Player) sender);
        } catch (ArrayIndexOutOfBoundsException e) {
            debug("Catched ArrayIndexOutOfBoundException!");
            sendPlayerMessage(sender, Lang.ERROR_PLAYER_NOT_ENOUGH_ARGUMENT);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, List<String> args) {
        return (!(Arrays.toString(args.toArray()).equalsIgnoreCase("[]"))) ? new ArrayList<>() : new ArrayList<>(main.getItemManager().getItemMap().keySet());
    }
}
