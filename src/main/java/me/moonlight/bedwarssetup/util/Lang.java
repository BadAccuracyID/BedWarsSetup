package me.moonlight.bedwarssetup.util;

import lombok.Getter;

import static me.moonlight.bedwarssetup.util.MethodUtils.color;

// language enum class for messages
public enum Lang {

    ERROR_PLAYER_ONLY_COMMAND("&cThis is a player only command!"),
    ERROR_PLAYER_WRONG_ARGUMENT("&cWrong argument! Correct usage: {0}"),
    ERROR_PLAYER_NOT_ENOUGH_ARGUMENT("&cNot enough argument! Correct usage: {0}"),
    ERROR_PLAYER_COMMAND_DOESNT_EXIST("&cCommand doesn't exist! Try /bs help");

    @Getter public String message;

    Lang(String message) {
        this.message = color(message);
    }
}
