package me.moonlight.bedwarssetup;

import com.andrei1058.bedwars.api.BedWars;
import lombok.Getter;
import lombok.Setter;
import me.moonlight.bedwarssetup.commands.BedwarsSetupCommand;
import me.moonlight.bedwarssetup.commands.DebugSubCommand;
import me.moonlight.bedwarssetup.listener.ChatListener;
import me.moonlight.bedwarssetup.listener.InventoryListener;
import me.moonlight.bedwarssetup.listener.SetupSessionListener;
import me.moonlight.bedwarssetup.manager.ItemManager;
import me.moonlight.bedwarssetup.manager.SubCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static me.moonlight.bedwarssetup.util.MethodUtils.error;
import static me.moonlight.bedwarssetup.util.MethodUtils.log;

/**
 * BedwarsSetup main class
 *
 * BedWarsSetup is a plugin to help your setup
 * experience with {@link BedWars} (BedWars1058) easier
 * @version 1.0.5
 */
public class Main extends JavaPlugin {

    @Getter @Setter private boolean debug;
    // bedwars1058 instance
    @Getter private BedWars bedWars;
    // itemManager instance
    @Getter private ItemManager itemManager;
    // subCommandManager instance
    @Getter private SubCommandManager subCommandManager;

    @Override
    public void onEnable() {
        log("Starting BedwarsSetup v" + getDescription().getVersion() + " ...");
        this.bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        // check if bedwars1058 is null or not
        if(this.bedWars == null) {
            error("&cBedWars1058 is not active! disabling...");
            this.setEnabled(false);
        }
        // register manager instances
        this.itemManager = new ItemManager(this);
        this.subCommandManager = new SubCommandManager(this);
        // register commands
        new BedwarsSetupCommand(this);
        // register listeners
        new ChatListener(this);
        new InventoryListener(this);
        new SetupSessionListener(this);
    }

    @Override
    public void onDisable() {
        log("Stopping BedwarsSetup v" + getDescription().getVersion() + " ...");
    }

}
