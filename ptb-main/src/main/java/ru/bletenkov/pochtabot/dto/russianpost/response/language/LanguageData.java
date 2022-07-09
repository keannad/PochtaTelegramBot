package ru.bletenkov.pochtabot.dto.russianpost.response.language;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "LanguageData", namespace = "http://russianpost.org/operationhistory/data")
@XmlAccessorType(XmlAccessType.FIELD)
public class LanguageData {

    @XmlElement(name = "Language", namespace = "http://russianpost.org/operationhistory/data")
    private List<Language> languages = new ArrayList<>();

    @NoArgsConstructor
    @Getter
    @Setter
    @XmlRootElement(name = "Language", namespace = "http://russianpost.org/operationhistory/data")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Language{

        @XmlAttribute(name = "name")
        private String code;

        @XmlAttribute(name = "code")
        private String name;

    }
}
