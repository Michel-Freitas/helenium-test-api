package com.heleniumTest.HeleniumTestApi.model;

import lombok.Data;

import java.util.List;

@Data
public class ReportElement {

    private List<ReportElementRecord> records;
}
