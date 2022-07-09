package ru.bletenkov.pochtabot.service;

import ru.bletenkov.pochtabot.dto.russianpost.response.language.LanguageData;
import ru.bletenkov.pochtabot.service.impl.PostSOAPSingleServiceImpl;

import javax.xml.soap.SOAPMessage;
import java.util.List;

public interface PostRequestService {

    SOAPMessage generateHistoryRequest(String barcode);

    SOAPMessage generateLanguageRequest();

    PostSOAPSingleServiceImpl.RussianPostResponse parseHistoryRequest(SOAPMessage message);

    List<LanguageData.Language> parseLanguageRequest(SOAPMessage message);
}
