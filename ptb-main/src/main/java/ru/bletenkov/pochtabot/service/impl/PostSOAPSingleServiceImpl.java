package ru.bletenkov.pochtabot.service.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import ru.bletenkov.pochtabot.dto.history.HistoryDto;
import ru.bletenkov.pochtabot.dto.russianpost.request.AuthorizationHeader;
import ru.bletenkov.pochtabot.dto.russianpost.request.language.LanguageRequest;
import ru.bletenkov.pochtabot.dto.russianpost.request.single.OperationHistory;
import ru.bletenkov.pochtabot.dto.russianpost.request.single.OperationHistoryRequest;
import ru.bletenkov.pochtabot.dto.russianpost.response.language.LanguageData;
import ru.bletenkov.pochtabot.dto.russianpost.response.language.LanguageResponse;
import ru.bletenkov.pochtabot.dto.russianpost.response.single.OperationHistoryResponse;
import ru.bletenkov.pochtabot.dto.russianpost.response.single.OperationParameters;
import ru.bletenkov.pochtabot.service.PostRequestService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PostSOAPSingleServiceImpl implements PostRequestService {

    @Value("${russianpost.login}")
    private String login;

    @Value("${russianpost.password}")
    private String password;

    @Override
    public SOAPMessage generateHistoryRequest(String barcode) {

        OperationHistory oh = new OperationHistory();

        OperationHistoryRequest request = new OperationHistoryRequest();
        request.setBarcode(barcode);

        AuthorizationHeader header = new AuthorizationHeader();
        header.setLogin(login);
        header.setPassword(password);

        oh.setRequest(request);
        oh.setAuthorizationHeader(header);

        try {
            MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
            SOAPMessage message = messageFactory.createMessage();

            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            SOAPHeader soapHeader = soapEnvelope.getHeader();
            SOAPBody soapBody = soapEnvelope.getBody();

            setSoapEnvelop(soapEnvelope);

            soapEnvelope.setPrefix("soap");
            soapHeader.setPrefix("soap");
            soapBody.setPrefix("soap");
            soapEnvelope.removeNamespaceDeclaration("env");

            JAXBContext context = JAXBContext.newInstance(OperationHistory.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            marshaller.marshal(oh, soapBody);
            message.saveChanges();
            return message;

        } catch (SOAPException | JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SOAPMessage generateLanguageRequest() {

        LanguageRequest lr = new LanguageRequest();

        AuthorizationHeader header = new AuthorizationHeader();
        header.setLogin(login);
        header.setPassword(password);

        lr.setHeader(header);

        try {
            MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
            SOAPMessage message = messageFactory.createMessage();

            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            SOAPHeader soapHeader = soapEnvelope.getHeader();
            SOAPBody soapBody = soapEnvelope.getBody();

            setSoapEnvelop(soapEnvelope);

            soapEnvelope.setPrefix("soap");
            soapHeader.setPrefix("soap");
            soapBody.setPrefix("soap");
            soapEnvelope.removeNamespaceDeclaration("env");

            JAXBContext context = JAXBContext.newInstance(LanguageRequest.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            marshaller.marshal(lr, soapBody);
            message.saveChanges();
            return message;

        } catch (SOAPException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RussianPostResponse parseHistoryRequest(SOAPMessage message) {

        RussianPostResponse resp = new RussianPostResponse();
        ArrayList<HistoryDto> historyRecords = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(OperationHistoryResponse.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

//            OperationHistoryResponse response =
//                    (OperationHistoryResponse) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
            OperationHistoryResponse response =
                    (OperationHistoryResponse) unmarshaller.unmarshal(new InputStreamReader(
                            new FileInputStream("response.xml"), StandardCharsets.UTF_8));

            response.getOperationHistoryData().getHistoryRecords().forEach(hr -> {
                HistoryDto dto = new HistoryDto();

                OperationParameters op = hr.getOperationParameters();
                dto.setTypeId(op.getOperType().getId());
                dto.setTypeName(op.getOperType().getName());
                dto.setAttributeId(op.getOperAttribute().getId());
                dto.setAttributeName(op.getOperAttribute().getName());

                dto.setOperDate(hr.getOperationParameters().getOperDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime());

                historyRecords.add(dto);

                resp.setPackageCode(hr.getItemParameters().getBarcode());
                resp.setDprtCountry(hr.getAddressParameters().getCountryFrom().getNameRu());
                resp.setArvdCountry(hr.getAddressParameters().getCountryTo().getNameRu());
            });

            resp.setRecords(historyRecords);
        } catch (JAXBException | FileNotFoundException ignore) {}

        return resp;
    }

    @Override
    public List<LanguageData.Language> parseLanguageRequest(SOAPMessage message) {

        List<LanguageData.Language> languageList = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(LanguageResponse.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            LanguageResponse response =
                    (LanguageResponse) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());

            languageList.addAll(response.getLanguageData().getLanguages());

        } catch (SOAPException | JAXBException ignore) {}

        return languageList;
    }

    private void setSoapEnvelop(SOAPEnvelope soapEnvelope){

        try {
            soapEnvelope.addNamespaceDeclaration("soap","http://www.w3.org/2003/05/soap-envelope");
            soapEnvelope.addNamespaceDeclaration("oper","http://russianpost.org/operationhistory");
            soapEnvelope.addNamespaceDeclaration("data","http://russianpost.org/operationhistory/data");
            soapEnvelope.addNamespaceDeclaration("ns1","http://schemas.xmlsoap.org/soap/envelope/");
        } catch (SOAPException e) {
            throw new RuntimeException(e);
        }

    }

    @NoArgsConstructor
    @Data
    public class RussianPostResponse{

        private String packageCode;
        private String dprtCountry;
        private String arvdCountry;
        private List<HistoryDto> records;

    }
}
