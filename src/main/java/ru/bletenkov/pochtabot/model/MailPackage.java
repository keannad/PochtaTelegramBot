package ru.bletenkov.pochtabot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MailPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String code;
    private Long lastStateId;

    private boolean isDead;

}
