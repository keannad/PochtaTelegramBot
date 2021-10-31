package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.model.HistoryRecord;

import java.util.ArrayList;

public interface PostService {

    ArrayList<HistoryRecord> getOperationHistory(String string);
    
}
