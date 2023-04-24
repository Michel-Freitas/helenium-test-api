package com.heleniumTest.HeleniumTestApi.model;

import lombok.Data;

@Data
public class ReportElementRecord {

    private String className;
    private String methodName;
    private ReportElementLocator failedLocator;
    private ReportElementLocator healedLocator;
    private String screenShotPath;
    private int healingResultId;
}
