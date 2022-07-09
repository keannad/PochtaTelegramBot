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

@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AddressParameters", namespace = "http://russianpost.org/operationhistory/data")
public class AddressParameters implements Serializable {

    @XmlElement(name = "DestinationAddress", namespace = "http://russianpost.org/operationhistory/data")
    private Address destAddress;

    @XmlElement(name = "OperationAddress", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Address operAddress;

    @XmlElement(name = "MailDirect", namespace = "http://russianpost.org/operationhistory/data")
    private Country mailDirect;

    @XmlElement(name = "CountryFrom", namespace = "http://russianpost.org/operationhistory/data")
    private Country countryFrom;

    @XmlElement(name = "CountryOper", namespace = "http://russianpost.org/operationhistory/data", required = true)
    private Country countryTo;

    public void setDestAddress(Address destAddress) {
        this.destAddress = destAddress;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    @XmlRootElement(namespace = "http://russianpost.org/operationhistory/data")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {
            "index",
            "description"
    })
    public static class Address implements Serializable {

        @XmlElement(name = "Index", namespace = "http://russianpost.org/operationhistory/data", required = true)
        private String index;

        @XmlElement(name = "Description", namespace = "http://russianpost.org/operationhistory/data")
        private String description;

    }

    @NoArgsConstructor
    @Getter
    @Setter
    @XmlRootElement(namespace = "http://russianpost.org/operationhistory/data")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {
            "id",
            "code2A",
            "code3A",
            "nameRu",
            "nameEn"
    })
    public static class Country implements Serializable {

        @XmlElement(name = "Id", namespace = "http://russianpost.org/operationhistory/data", required = true)
        private Integer id;

        @XmlElement(name = "Code2A", namespace = "http://russianpost.org/operationhistory/data")
        private String code2A;

        @XmlElement(name = "Code3A", namespace = "http://russianpost.org/operationhistory/data")
        private String code3A;

        @XmlElement(name = "NameRU", namespace = "http://russianpost.org/operationhistory/data")
        private String nameRu;

        @XmlElement(name = "NameEN", namespace = "http://russianpost.org/operationhistory/data")
        private String nameEn;

    }
}
