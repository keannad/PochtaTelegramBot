package ru.bletenkov.pochtabot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "is_admin")
    private boolean isAdmin;

}
