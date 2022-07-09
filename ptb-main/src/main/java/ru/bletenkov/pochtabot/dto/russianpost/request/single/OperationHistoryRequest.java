package ru.bletenkov.pochtabot.dto.russianpost.request.single;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "OperationHistoryRequest", namespace = "http://russianpost.org/operationhistory/data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "barcode",
        "messageType",
        "language"
})
public class OperationHistoryRequest {

    @XmlElement(name = "Barcode", namespace = "http://russianpost.org/operationhistory/data")
    private String barcode;
    @XmlElement(name = "MessageType", namespace = "http://russianpost.org/operationhistory/data")
    private Integer messageType = 0;
    @XmlElement(name = "Language", namespace = "http://russianpost.org/operationhistory/data")
    private String language = "RUS";

}
