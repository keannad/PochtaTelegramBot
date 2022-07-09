package ru.bletenkov.pochtabot.dto.russianpost.response.single;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OperationHistoryData", namespace = "http://russianpost.org/operationhistory/data")
public class OperationHistoryData implements Serializable {

    @XmlElement(name = "historyRecord", namespace = "http://russianpost.org/operationhistory/data")

    private List<OperationHistoryRecord> historyRecords = new ArrayList<>();

}
