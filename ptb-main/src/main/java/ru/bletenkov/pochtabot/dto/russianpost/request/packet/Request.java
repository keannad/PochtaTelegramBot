package ru.bletenkov.pochtabot.dto.russianpost.request.packet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "fileName",
        "fileTypeID",
        "fileNumber",
        "senderID",
        "recipientID",
        "datePreparation",
        "item"
})
public class Request {

    @XmlAttribute(name = "FileName", required = true)
    private String fileName;
    @XmlAttribute(name = "FileTypeID", required = true)
    private Integer fileTypeID;
    @XmlAttribute(name = "FileNumber", required = true)
    private Integer fileNumber;
    @XmlAttribute(name = "SenderID", required = true)
    private Integer senderID;
    @XmlAttribute(name = "RecipientID", required = true)
    private String recipientID;
    @XmlAttribute(name = "DatePreparation", required = true)
    private String datePreparation;

    @XmlElement(name = "Item")
    private List<BarcodeItem> item;


}
