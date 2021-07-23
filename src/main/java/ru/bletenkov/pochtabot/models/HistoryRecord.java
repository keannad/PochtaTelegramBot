package ru.bletenkov.pochtabot.models;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       20.07.2021
    @project:    PochtaTelegramBot
*/

import java.util.Date;
import java.text.SimpleDateFormat;

public class HistoryRecord {

    private int operTypeId;
    private String operTypeName;
    private Date operDate;

    private int operAttrId;
    private String operAttrName;

    public int getOperTypeId() {
        return operTypeId;
    }

    public void setOperTypeId(int operTypeId) {
        this.operTypeId = operTypeId;
    }

    public String getOperTypeName() {
        return operTypeName;
    }

    public void setOperTypeName(String operTypeName) {
        this.operTypeName = operTypeName;
    }

    public String getOperDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return simpleDateFormat.format(operDate);
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public int getOperAttrId() {
        return operAttrId;
    }

    public void setOperAttrId(int operAttrId) {
        this.operAttrId = operAttrId;
    }

    public String getOperAttrName() {
        return operAttrName != null ? operAttrName : "";
    }

    public void setOperAttrName(String operAttrName) {
        this.operAttrName = operAttrName;
    }
}
