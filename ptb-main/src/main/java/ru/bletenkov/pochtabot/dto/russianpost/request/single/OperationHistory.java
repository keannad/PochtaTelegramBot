package ru.bletenkov.pochtabot.dto.russianpost.request.single;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bletenkov.pochtabot.dto.russianpost.request.AuthorizationHeader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "getOperationHistory", namespace = "http://russianpost.org/operationhistory")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "request",
        "authorizationHeader"
})
public class OperationHistory {

    @XmlElement(name = "OperationHistoryRequest", namespace = "http://russianpost.org/operationhistory/data")
    private OperationHistoryRequest request;
    @XmlElement(name = "AuthorizationHeader", namespace = "http://russianpost.org/operationhistory/data")
    private AuthorizationHeader authorizationHeader;

}
