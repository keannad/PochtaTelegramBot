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
@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "historyRecord", namespace = "http://russianpost.org/operationhistory/data")
public class OperationHistoryRecord implements Serializable {

    @XmlElement(name = "AddressParameters", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private AddressParameters addressParameters;

    @XmlElement(name = "FinanceParameters", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private FinanceParameters financeParameters;

    @XmlElement(name = "ItemParameters", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private ItemParameters itemParameters;

    @XmlElement(name = "OperationParameters", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private OperationParameters operationParameters;

    @XmlElement(name = "UserParameters", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private UserParameters userParameters;

}
