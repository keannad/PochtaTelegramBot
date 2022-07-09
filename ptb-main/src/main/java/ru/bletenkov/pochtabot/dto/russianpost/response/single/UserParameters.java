package ru.bletenkov.pochtabot.dto.russianpost.response.single;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UserParameters", namespace = "http://russianpost.org/operationhistory/data")
public class UserParameters implements Serializable {

    @XmlElement(name = "SendCtg", namespace = "http://russianpost.org/operationhistory/data")
    private SendCategory sendCategory;
    @XmlElement(name = "Sndr", namespace = "http://russianpost.org/operationhistory/data")
    private String sender;
    @XmlElement(name = "Rcpn", namespace = "http://russianpost.org/operationhistory/data")
    private String recipient;

    @NoArgsConstructor
    @Getter
    @Setter
    @XmlRootElement(namespace = "http://russianpost.org/operationhistory/data")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {
            "id",
            "name"
    })
    public static class SendCategory implements Serializable {

        @XmlElement(name = "Id", namespace = "http://russianpost.org/operationhistory/data")
        private String id;

        @XmlElement(name = "Name", namespace = "http://russianpost.org/operationhistory/data")
        private String name;

    }
}
