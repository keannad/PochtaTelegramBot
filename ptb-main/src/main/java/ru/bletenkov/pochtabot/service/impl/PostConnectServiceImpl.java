package ru.bletenkov.pochtabot.service.impl;

import org.springframework.beans.factory.annotation.Value;
import ru.bletenkov.pochtabot.service.PostConnectService;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class PostConnectServiceImpl implements PostConnectService {

    @Value("${russianpost.url}")
    private String soapUrl;

    @Override
    public SOAPMessage getSingleResponse(SOAPMessage request) {

        try {
            SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnFactory.createConnection();

            SOAPMessage soapResponse = connection.call(request, soapUrl);
            connection.close();

            return soapResponse;

        }catch (SOAPException e) {
            e.printStackTrace();
            return null;
        }
    }

}
