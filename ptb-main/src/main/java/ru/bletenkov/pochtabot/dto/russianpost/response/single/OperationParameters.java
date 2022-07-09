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
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OperationParameters", namespace = "http://russianpost.org/operationhistory/data")
@XmlType(propOrder = {
        "operType",
        "operAttribute",
        "operDate"
})
public class OperationParameters implements Serializable {

    @XmlElement(name = "OperType", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Rtm02Parameter operType;
    @XmlElement(name = "OperAttr", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Rtm02Parameter operAttribute;
    @XmlElement(name = "OperDate", namespace = "http://russianpost.org/operationhistory/data", type = Date.class, required = true)
    private Date operDate;

}
