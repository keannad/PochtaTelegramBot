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
@XmlRootElement(name = "getOperationHistoryResponse", namespace = "http://russianpost.org/operationhistory")
public class OperationHistoryResponse implements Serializable {

    @XmlElement(name = "OperationHistoryData", namespace = "http://russianpost.org/operationhistory/data")
    private OperationHistoryData operationHistoryData;

}
