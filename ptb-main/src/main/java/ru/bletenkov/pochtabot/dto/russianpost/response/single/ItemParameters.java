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
@XmlRootElement(name = "ItemParameters", namespace = "http://russianpost.org/operationhistory/data")
public class ItemParameters implements Serializable {

    @XmlElement(name = "Barcode", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private String barcode;
    @XmlElement(name = "Internum", namespace = "http://russianpost.org/operationhistory/data")
    private String internum;
    @XmlElement(name = "ValidRuType", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Boolean validRuType;
    @XmlElement(name = "ValidEnType", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Boolean validEnType;
    @XmlElement(name = "ComplexItemName", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private String complexItemName;

    @XmlElement(name = "MailRank", namespace = "http://russianpost.org/operationhistory/data")
    private Rtm02Parameter mailRank;
    @XmlElement(name = "PostMark", namespace = "http://russianpost.org/operationhistory/data")
    private Rtm02Parameter postMark;
    @XmlElement(name = "MailType", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Rtm02Parameter mailType;
    @XmlElement(name = "MailCtg", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Rtm02Parameter mailCategory;

    @XmlElement(name = "Mass", namespace = "http://russianpost.org/operationhistory/data")
    private Integer mass;
    @XmlElement(name = "MaxMassRu", namespace = "http://russianpost.org/operationhistory/data")
    private Integer maxMassRu;
    @XmlElement(name = "MaxMassEn", namespace = "http://russianpost.org/operationhistory/data")
    private Integer maxMassEn;

}
