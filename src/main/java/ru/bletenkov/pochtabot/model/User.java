package ru.bletenkov.pochtabot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "is_admin")
    private boolean isAdmin;

}
