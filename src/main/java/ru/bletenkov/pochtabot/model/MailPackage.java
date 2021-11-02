package ru.bletenkov.pochtabot.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mail_package")
@NoArgsConstructor
@Setter
@Getter
public class MailPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private User user;

    @Column(name = "code")
    private String code;

    @Column(name = "is_dead")
    private boolean isDead;

    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    public User getUser() {
        return this.user;
    }

}
