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
@XmlRootElement(namespace = "http://russianpost.org/operationhistory/data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "id",
        "name"
})
public class Rtm02Parameter implements Serializable {

    @XmlElement(name = "Id", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Integer id;

    @XmlElement(name = "Name", namespace = "http://russianpost.org/operationhistory/data")
    private String name;

}
