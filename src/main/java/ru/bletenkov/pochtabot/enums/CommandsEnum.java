package ru.bletenkov.pochtabot.enums;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

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
