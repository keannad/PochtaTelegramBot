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

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
public class BarcodeItem {

    @XmlAttribute(name = "Barcode", namespace = "http://fclient.russianpost.org", required = true)
    private String barcode;

    @XmlElement(name = "Operation")
    private Operation operation;

    @XmlElement(name = "Error")
    private Error error;

    @NoArgsConstructor
    @Getter
    @Setter
    @XmlRootElement(name = "Operation")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {
            "operTypeID",
            "operCtgID",
            "operName",
            "dateOper",
            "indexOper"

    })
    public class Operation {

        @XmlAttribute(name = "OperTypeID", required = true)
        private Integer operTypeID;
        @XmlAttribute(name = "OperCtgID", required = true)
        private Integer operCtgID;
        @XmlAttribute(name = "OperName", required = true)
        private String operName;
        @XmlAttribute(name = "DateOper", required = true)
        private String dateOper;
        @XmlAttribute(name = "IndexOper", required = true)
        private String indexOper;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    @XmlRootElement(name = "Error", namespace = "http://fclient.russianpost.org")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {
            "errorTypeID",
            "errorName"
    })
    public class Error {

        @XmlAttribute(name = "ErrorTypeID", required = true)
        private Integer errorTypeID;
        @XmlAttribute(name = "ErrorName", required = true)
        private String errorName;
    }
}
