package ru.bletenkov.pochtabot.utils;

import java.util.Arrays;
import java.util.List;

public enum CommandsEnum {
    START,
    REGISTER,
    ADD,
    DELETE,
    LAST,
    TRACK;

    @Override
    public String toString() {
        return name() + "COMMAND";
    }

    public static List<CommandsEnum> getAllCommands(){
        return Arrays.asList(values());
    }
}
