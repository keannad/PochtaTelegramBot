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

    private boolean isLast;

    //Oper type ID
    public int getOperTypeId() {
        return operTypeId;
    }

    public void setOperTypeId(int operTypeId) {
        this.operTypeId = operTypeId;
    }

    //Oper type name
    public String getOperTypeName() {
        return operTypeName;
    }

    public void setOperTypeName(String operTypeName) {
        this.operTypeName = operTypeName;
    }

    //Oper date
    public String getOperDateISOString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return formatter.format(operDate);
    }

    public String getOperDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return simpleDateFormat.format(operDate);
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    //Oper Attr ID
    public int getOperAttrId() {
        return operAttrId;
    }

    public void setOperAttrId(int operAttrId) {
        this.operAttrId = operAttrId;
    }

    //Oper Attr Name
    public String getOperAttrName() {
        return operAttrName != null ? operAttrName : "";
    }

    public void setOperAttrName(String operAttrName) {
        this.operAttrName = operAttrName;
    }

    //Is last
    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
