package ru.bletenkov.pochtabot.models;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       19.07.2021
    @project:    PochtaTelegramBot
*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PackageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private String code;
    private String lastStateString;
    private String lastStateDate;

    private boolean isDead;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastStateString() {
        return lastStateString;
    }

    public void setLastStateString(String lastStateString) {
        this.lastStateString = lastStateString;
    }

    public String getLastStateDate() {
        return lastStateDate;
    }

    public void setLastStateDate(String lastStateDate) {
        this.lastStateDate = lastStateDate;
    }

    public boolean isIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
}