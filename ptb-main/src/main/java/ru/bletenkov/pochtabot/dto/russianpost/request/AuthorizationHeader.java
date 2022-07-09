package ru.bletenkov.pochtabot.dto.russianpost.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "AuthorizationHeader", namespace = "http://russianpost.org/operationhistory/data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "login",
        "password"
})
public class AuthorizationHeader {

    @XmlElement(name = "login", namespace = "http://russianpost.org/operationhistory/data")
    private String login;
    @XmlElement(name = "password", namespace = "http://russianpost.org/operationhistory/data")
    private String password;
    @XmlAttribute(name = "mustUnderstand", namespace = "http://russianpost.org/operationhistory/data")
    private Integer mustUnderstand = 1;

}
