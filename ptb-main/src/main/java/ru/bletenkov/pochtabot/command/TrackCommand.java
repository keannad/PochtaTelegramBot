package ru.bletenkov.pochtabot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bletenkov.pochtabot.dto.history.HistoryDto;
import ru.bletenkov.pochtabot.dto.mailpackage.MailPackageDto;
import ru.bletenkov.pochtabot.service.MailPackageService;
import ru.bletenkov.pochtabot.service.PostConnectService;
import ru.bletenkov.pochtabot.service.PostRequestService;
import ru.bletenkov.pochtabot.service.UserService;
import ru.bletenkov.pochtabot.service.impl.PostSOAPSingleServiceImpl;

import javax.xml.soap.SOAPMessage;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

@Slf4j
public class TrackCommand extends AbstractCommand {

    private final PostConnectService postConnectService;

    private final PostRequestService postRequestService;

    private final UserService userService;
    private final MailPackageService mailPackageService;

    public TrackCommand(UserService userService, MailPackageService mailPackageService,
                        PostConnectService postConnectService, PostRequestService postParseService) {

        super("track", "Track one parcel");

        this.userService = userService;
        this.mailPackageService = mailPackageService;
        this.postConnectService = postConnectService;
        this.postRequestService = postParseService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        Long chatId = chat.getId();

        StringBuilder messageString = new StringBuilder();
        if(strings.length == 0){
            messageString.append("Track single parcel by tracking number \n /track [Number]");
            sendMessage(absSender, chatId, messageString.toString());
            return;
        }

        if(strings.length > 1){
            messageString.append("You can track only single parcel per request \n /track [Number] \n");
        }

        String packageCode = strings[0];

        SOAPMessage serviceRequest = postRequestService.generateHistoryRequest(packageCode);
        SOAPMessage serviceResponse = postConnectService.getSingleResponse(serviceRequest);
        PostSOAPSingleServiceImpl.RussianPostResponse response = postRequestService.parseHistoryRequest(serviceResponse);
        ArrayList<HistoryDto> historyRecords = (ArrayList<HistoryDto>) response.getRecords();

        messageString.append("<b>[").append(Objects.requireNonNullElse(response.getPackageCode(), packageCode)).append("]").append("</b>\n");
        if (!historyRecords.isEmpty()) {
            messageString.append("<i>").append(response.getDprtCountry()).append("</i> <b>➝</b> <i>")
                    .append(response.getArvdCountry()).append("</i>\n\n");

            for (HistoryDto historyRecord : historyRecords) {
                messageString.append(generateTableRow(historyRecord, historyRecords.indexOf(historyRecord) + 1));
                messageString.append("\n");
            }
        } else {
            messageString.append("No history records found!");
        }

        if (userService.isRegistered(chatId)){
            MailPackageDto mailPackage = mailPackageService.getByCodeAndUserId(chatId, packageCode);
            if (mailPackage != null){
                mailPackageService.save(mailPackage);
            }
        }

        sendMessage(absSender, chatId, messageString.toString());
    }

    private String getMessageString(String type, String attribute) {

        if (attribute != null && !attribute.isEmpty()) {
            return type + ":\n <i><b>" + attribute + "</b></i>";
        }

        return "<i><b>" + type + "</b></i>";
    }

    private String generateTableRow(HistoryDto historyDto, Integer counter) {

        return counter + ") <b>" +
                DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm").format(historyDto.getOperDate()) +
                "</b>\n" +
                getMessageString(historyDto.getTypeName(), historyDto.getAttributeName()) +
                "\n";

    }

}
