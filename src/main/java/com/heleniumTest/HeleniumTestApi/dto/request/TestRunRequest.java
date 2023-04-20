package com.heleniumTest.HeleniumTestApi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestRunRequest {
    private String seleniumCode;
    private String testName;
}
