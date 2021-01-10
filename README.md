# BedWars Setup - Minecraft Plugin
### What is BedWars Setup?
BedWars Setup is a minecraft plugin to help setting up
BedWars arena using [BedWars1058 plugin](https://www.spigotmc.org/resources/bedwars1058-the-most-modern-bedwars-plugin-bungee-auto-scale-bungee-legacy-multiarena-shared.50942/).
<br/>
### Setup Commands
#### /bs - Main setup commands, containing subcommands:
##### /bs giveitem \<itemId>
give a setup item based on their ID (Item ID can be found [here](https://github.com/KJosh541/BedWarsSetup/#setup-items))
##### /bs setteam \<teamName>
set the current setup team, used in some setup items
##### /bs help
show the command list
##### /bs menu
open a menu containing setup items
##### /bs debug \<true | false>
set the debug to true or false, debug is disabled for the time being
<p>REMINDER: DON'T USE <> ON THE COMMANDS!</p>

### Setup Items
These items are used to make your setup experience with BedWars1058 easier!
They can be used by **right clicking** or **left clicking** them, you can see their usage in their lores.
Please note that some of these item can only be used during an Arena Setup Session ([BedWars1058](https://www.spigotmc.org/resources/bedwars1058-the-most-modern-bedwars-plugin-bungee-auto-scale-bungee-legacy-multiarena-shared.50942/))
Here's some further detail:
#### Setup Diamond (Item: Diamond)
- **(RIGHT CLICK)** Round up your yaw to 0, 90, 180, 270 (North, East, South, West) depending on your current yaw (e.g. if your current yaw is 60,  then your yaw will be set to 90 or to east)
- **(LEFT CLICK)** Teleport you to the middle of the block you are standing on.
  ID: SETUP_DIAMOND
#### Arena Cookie (Item: Cookie) **SETUP SESSION ONLY**
- **(RIGHT CLICK)** Set the waiting spawn of the current arena to your current location.
- **(LEFT CLICK)** Set the spectator spawn of the current arena to your current location.
  ID: ARENA_COOKIE
#### Waiting Pos Tool (Item: Feather) **SETUP SESSION ONLY**
- **(RIGHT CLICK)** Set the first waiting pos of the current arena.
- **(LEFT CLICK)** Set the second waiting pos of the current arena.
  ID: WAITING_POS_TOOL
#### Set Spawn Tool (Item: Dragon Egg) **SETUP SESSION ONLY**
- **(RIGHT CLICK)** Set the current team's spawn (set your team [here](https://github.com/KJosh541/BedWarsSetup/#bs-setteam-teamname))
#### Set Shop and Upgrade Tool (Item: Emerald) **SETUP SESSION ONLY**
- **(RIGHT CLICK)** Set the current team's shop to your current location
- **(LEFT CLICK)** Set the current team's upgrade to your location
#### Team Generator Tool (Item: Iron Ingot) **SETUP SESSION ONLY**
- **(RIGHT CLICK)** Set the current team's generator to your current location
- **(LEFT CLICK)** Set the current team's kill spawn to your current location
#### Add Generator Tool **SETUP SESSION ONLY**
- **(RIGHT CLICK)** Create a new generator, depending on the block you are currently (emerald or diamond)
