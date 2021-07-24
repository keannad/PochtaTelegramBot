package ru.bletenkov.pochtabot.services;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       22.07.2021
    @project:    PochtaTelegramBot
*/

import ru.bletenkov.pochtabot.models.HistoryRecord;

import java.util.ArrayList;

public interface PostService {

    ArrayList<HistoryRecord> getOperationHistory(String string);

}
