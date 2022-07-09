package ru.bletenkov.pochtabot.dto.russianpost.response.single;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FinanceParameters", namespace = "http://russianpost.org/operationhistory/data")
public class FinanceParameters implements Serializable {

    @XmlElement(name = "Payment", namespace = "http://russianpost.org/operationhistory/data")
    private Integer payment;

    @XmlElement(name = "Value", namespace = "http://russianpost.org/operationhistory/data")
    private Integer value;

    @XmlElement(name = "MassRate", namespace = "http://russianpost.org/operationhistory/data")
    private Integer massRate;

    @XmlElement(name = "InsrRate", namespace = "http://russianpost.org/operationhistory/data")
    private Integer insrRate;

    @XmlElement(name = "AirRate", namespace = "http://russianpost.org/operationhistory/data")
    private Integer airRate;

    @XmlElement(name = "Rate", namespace = "http://russianpost.org/operationhistory/data")
    private Integer rate;

    @XmlElement(name = "CustomDuty", namespace = "http://russianpost.org/operationhistory/data")
    private Integer customDuty;

}
