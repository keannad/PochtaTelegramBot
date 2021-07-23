package ru.bletenkov.pochtabot.services;
/*
    Created by IntelliJ IDEA
    @author:     Bletenkov Kirill aka Keannad
    @date:       20.07.2021
    @project:    PochtaTelegramBot
*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import ru.bletenkov.pochtabot.models.HistoryRecord;

import javax.xml.soap.*;
import javax.xml.xpath.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PostSOAPSingleService implements PostService{

    private final String login = "";
    private final String password = "";

    private String soapUrl = "https://tracking.russianpost.ru/rtm34";

    @Override
    public String getOperationHistory(String barcode){

        StringBuilder statusString = new StringBuilder();

        try {
            SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnFactory.createConnection();

            //Cоздаем сообщение
            MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.2 Protocol");
            SOAPMessage message = messageFactory.createMessage();
            SOAPPart soapPart = message.getSOAPPart();

            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            setSoapEnvelop(soapEnvelope);

            //Body
            SOAPBody soapBody = soapEnvelope.getBody();
            SOAPElement operElement = soapBody.addChildElement("getOperationHistory", "oper");
            setOperationHistoryRequest(operElement, barcode);
            setAuthorizationHeaders(operElement);

            message.saveChanges();

            SOAPMessage soapResponse = connection.call(message, soapUrl);
            Document document = soapResponse.getSOAPBody().extractContentAsDocument();

            SoapResponseParser parser = new SoapResponseParser(document);
            ArrayList<HistoryRecord> list = (ArrayList<HistoryRecord>) parser.parse();
            int counter = 1;
            for(HistoryRecord record : list){
                String singleString = String.format("%3d %32s %32s %s",
                        counter++,
                        record.getOperTypeName(),
                        record.getOperAttrName(),
                        record.getOperDate());
                statusString.append(singleString).append("\n");
            }

            //Закрываем соединение
            connection.close();

        } catch (SOAPException e) {
            e.printStackTrace();
        }

        return statusString.toString();
    }

    private void setSoapEnvelop(SOAPEnvelope soapEnvelope) throws SOAPException {

        soapEnvelope.addNamespaceDeclaration("soap","http://www.w3.org/2003/05/soap-envelope");
        soapEnvelope.addNamespaceDeclaration("oper","http://russianpost.org/operationhistory");
        soapEnvelope.addNamespaceDeclaration("data","http://russianpost.org/operationhistory/data");
        soapEnvelope.addNamespaceDeclaration("soapenv","http://schemas.xmlsoap.org/soap/envelope/");

    }

    private void setOperationHistoryRequest(SOAPElement element, String code){

        try {
            SOAPElement dataElement = element.addChildElement("OperationHistoryRequest","data");
            SOAPElement barcode = dataElement.addChildElement("Barcode","data");
            SOAPElement messageType = dataElement.addChildElement("MessageType","data");
            SOAPElement language = dataElement.addChildElement("Language","data");

            barcode.addTextNode(code);
            messageType.addTextNode("0");
            language.addTextNode("RUS");
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    private void setAuthorizationHeaders(SOAPElement element){

        try {
            SOAPElement dataAuth = element.addChildElement("AuthorizationHeader","data");
            SOAPFactory sf = SOAPFactory.newInstance();
            Name must = sf.createName("mustUnderstand","soapenv","http://schemas.xmlsoap.org/soap/envelope/");
            dataAuth.addAttribute(must,"1");
            SOAPElement login = dataAuth.addChildElement("login", "data");
            SOAPElement password = dataAuth.addChildElement("password","data");

            login.addTextNode(this.login);
            password.addTextNode(this.password);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    private class SoapResponseParser{

        private Document document;

        public SoapResponseParser(Document document){
            document.getDocumentElement().normalize();
            this.document = document;
        }

        public List<HistoryRecord> parse() {

            ArrayList<HistoryRecord> result = new ArrayList<>();

            Element historyData = (Element) document.getFirstChild().getFirstChild();

            NodeList childNodes = historyData.getChildNodes();
            for(int i =0; i < childNodes.getLength(); i++){
                Node childNode = childNodes.item(i);
                HistoryRecord tempRecord = new HistoryRecord();
                if(childNode.getNodeType() == Node.ELEMENT_NODE){
                    Element childElement = (Element) childNode;
                    Node operationParameters = childElement.getElementsByTagName("ns3:OperationParameters").item(0);
                    if (operationParameters.getNodeType() == Node.ELEMENT_NODE){
                        Element operationParametersElement = (Element) operationParameters;

                        //OperType element
                        Element operTypeElement = (Element) operationParametersElement.getElementsByTagName("ns3:OperType").item(0);
                        int operTypeId = Integer.parseInt(operTypeElement.getElementsByTagName("ns3:Id").item(0).getTextContent());
                        tempRecord.setOperTypeId(operTypeId);
                        tempRecord.setOperTypeName(operTypeElement.getElementsByTagName("ns3:Name").item(0).getTextContent());

                        //OperAttr element
                        int[] withOutAttr = {9, 10, 11, 13, 15, 16, 17, 18, 19, 20, 23, 25, 27, 28, 29, 30, 32 ,34, 37, 43, 44, 45, 46 ,47 ,48, 52};
                        if(IntStream.of(withOutAttr).noneMatch(x -> x == operTypeId)){
                            Element operAttrElement = (Element) operationParametersElement.getElementsByTagName("ns3:OperAttr").item(0);
                            int operAttrId = Integer.parseInt(operAttrElement.getElementsByTagName("ns3:Id").item(0).getTextContent());
                            tempRecord.setOperAttrId(operAttrId);
                            if (operAttrId != 0){
                                tempRecord.setOperAttrName(operAttrElement.getElementsByTagName("ns3:Name").item(0).getTextContent());
                            }
                        }

                        //OperDate element
                        Element operDateElement = (Element) operationParametersElement.getElementsByTagName("ns3:OperDate").item(0);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        String operDate = operDateElement.getTextContent();
                        try {
                            tempRecord.setOperDate(formatter.parse(operDate.substring(0, operDate.length() - 5)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                result.add(tempRecord);
            }

            return result;
        }
    }
}
