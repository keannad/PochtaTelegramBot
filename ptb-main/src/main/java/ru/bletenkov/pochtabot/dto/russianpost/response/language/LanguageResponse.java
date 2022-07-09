package ru.bletenkov.pochtabot.dto.russianpost.response.language;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "getLanguagesResponse", namespace = "http://russianpost.org/operationhistory")
@XmlAccessorType(XmlAccessType.FIELD)
public class LanguageResponse {

    @XmlElement(name = "LanguageData", namespace = "http://russianpost.org/operationhistory/data")
    private LanguageData languageData;
}
