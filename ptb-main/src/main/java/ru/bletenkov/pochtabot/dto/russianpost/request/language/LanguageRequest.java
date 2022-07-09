package ru.bletenkov.pochtabot.dto.russianpost.request.language;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bletenkov.pochtabot.dto.russianpost.request.AuthorizationHeader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "getLanguages", namespace = "http://russianpost.org/operationhistory")
@XmlAccessorType(XmlAccessType.FIELD)
public class LanguageRequest {

    @XmlElement(name = "AuthorizationHeader")
    private AuthorizationHeader header;
}
