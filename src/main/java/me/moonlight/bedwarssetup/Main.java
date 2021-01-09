package me.moonlight.bedwarssetup;

import com.andrei1058.bedwars.api.BedWars;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import me.moonlight.bedwarssetup.commands.BedwarsSetupCommand;
import me.moonlight.bedwarssetup.commands.DebugSubCommand;
import me.moonlight.bedwarssetup.commands.GiveSubCommand;
import me.moonlight.bedwarssetup.listener.Listener;
import me.moonlight.bedwarssetup.manager.ItemManager;
import me.moonlight.bedwarssetup.manager.SubCommandManager;
import me.moonlight.bedwarssetup.util.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

/**
 * BedwarsSetup main class
 * @version 1.0.2
 */
public class Main extends JavaPlugin {

    @Getter @Setter private boolean debug;
    @Getter private BedWars bedWars;
    @Getter private ItemManager itemManager;
    @Getter private SubCommandManager subCommandManager;

    @Override
    public void onEnable() {
        log("Starting BedwarsSetup v" + getDescription().getVersion() + " ...");
        this.bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        this.itemManager = new ItemManager(this);
        this.subCommandManager = new SubCommandManager(this);
        new DebugSubCommand(this);
        new BedwarsSetupCommand(this);
        new Listener(this);
    }

    @Override
    public void onDisable() {
        log("Stopping BedwarsSetup v" + getDescription().getVersion() + " ...");
    }

}
