package com.heleniumTest.HeleniumTestApi.service;

import com.heleniumTest.HeleniumTestApi.dto.request.TestRunRequest;

public interface ITestService {

    String testRun(TestRunRequest test);
}
