package com.heleniumTest.HeleniumTestApi.service;

import com.heleniumTest.HeleniumTestApi.dto.request.TestRunRequest;
import com.heleniumTest.HeleniumTestApi.model.Report;

import java.util.List;

public interface ITestService {

    Report testRun(TestRunRequest test);

    Report findReport(String testName);
}
