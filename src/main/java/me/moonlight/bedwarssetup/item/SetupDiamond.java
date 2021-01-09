package me.moonlight.bedwarssetup.item;

import me.moonlight.bedwarssetup.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.moonlight.bedwarssetup.util.MethodUtils.*;

/**
 * Setup Diamond item class
 *
 * This item is used to teleport the player to a middle of the
 * block and to round up the player's yaw to 0, 90, 180 or 270
 * (this depends on the player's current {@link Location})
 */
public class SetupDiamond extends AbstractItem {

    public SetupDiamond(Main main) {
        super(main, "SETUP_DIAMOND", Material.DIAMOND, 1, true, false, "&aSetup Diamond",
                "&6Mid-block teleport &e&lRIGHT CLICK",
                "&7Teleport to a middle of the block",
                "&7that you are currently on.",
                EMPTY_LINE,
                "&6Yaw Round Up &e&lLEFT CLICK",
                "&7Round up your yaw to 0, 90, 180, 270",
                "&7depending on your current yaw.",
                "&7Also round up your pitch to 0.");
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        Location location = player.getLocation();
        // teleport the player to the middle of the block by adding 0.5 to the location's block X and Z
        player.teleport(
                new Location(
                        player.getWorld(),
                        (location.getBlockX() + 0.5d),
                        location.getBlockY(),
                        (location.getBlockZ() + 0.5d),
                        player.getLocation().getYaw(),
                        player.getLocation().getPitch()
                )
        );
    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event, Player player) {
        event.setCancelled(true);
        // teleport the player to the correct yaw (0, 90, 270, 360)
        player.teleport(
            new Location(
                    player.getLocation().getWorld(),
                    player.getLocation().getX(),
                    player.getLocation().getY(),
                    player.getLocation().getZ(),
                    roundYaw(player.getLocation().getYaw()),
                    0.0f
            )
        );
    }
}
