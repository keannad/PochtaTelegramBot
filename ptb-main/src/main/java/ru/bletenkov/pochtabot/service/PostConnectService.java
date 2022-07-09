package ru.bletenkov.pochtabot.service;

import javax.xml.soap.SOAPMessage;

public interface PostConnectService {

    SOAPMessage getSingleResponse(SOAPMessage value);

}
