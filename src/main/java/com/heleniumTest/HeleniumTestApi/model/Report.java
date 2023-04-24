package com.heleniumTest.HeleniumTestApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity
@Table(name = "report")
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    private String uid;
    @Column(name = "create_date")
    private Date createDate;
    private String elements;

    @Transient
    private ReportElement mappedElements;
}
