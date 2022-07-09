package ru.bletenkov.pochtabot.dto.russianpost.request.packet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketRequest {

    @XmlElement(name = "request")
    private Request request;

    @XmlElement(name = "login", required = true)
    private String login;
    @XmlElement(name = "password", required = true)
    private String password;
    @XmlElement(name = "language")
    private String language = "RUS";

}
