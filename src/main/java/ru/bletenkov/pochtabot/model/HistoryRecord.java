package ru.bletenkov.pochtabot.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class HistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mail_package_id")
    private MailPackage mailPackage;

    private int recordTypeId;
    private String recordTypeName;
    private Date recordDate;
    private int recordAttrId;
    private String recordAttrName;

    private boolean isLast;

    //Oper date
    public String getOperDateISOString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return formatter.format(recordDate);
    }

    public String getOperDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return simpleDateFormat.format(recordDate);
    }

}
