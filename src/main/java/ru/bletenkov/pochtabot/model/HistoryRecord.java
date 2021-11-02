package ru.bletenkov.pochtabot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "history_records")
@NoArgsConstructor
@Setter
@Getter
public class HistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "package_id")
    private MailPackage mailPackage;

    @Column(name = "type_id")
    private int recordTypeId;

    @Column(name = "type_name")
    private String recordTypeName;

    @Column(name = "attribute_id")
    private int recordAttrId;

    @Column(name = "attribute_name")
    private String recordAttrName;

    @Column(name = "record_date")
    private Date recordDate;

    @Column(name = "is_last")
    private boolean isLast;

    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL, targetEntity = MailPackage.class)
    public MailPackage getMailPackage(){
        return this.mailPackage;
    }

    //Record date
    public String getRecordDateISOString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return formatter.format(recordDate);
    }

    public String getRecordDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return simpleDateFormat.format(recordDate);
    }

}
