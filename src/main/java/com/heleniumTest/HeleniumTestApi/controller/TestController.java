package com.heleniumTest.HeleniumTestApi.controller;

import com.heleniumTest.HeleniumTestApi.dto.request.TestRunRequest;
import com.heleniumTest.HeleniumTestApi.model.Report;
import com.heleniumTest.HeleniumTestApi.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test")
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    private ITestService testService;

    @PostMapping(value = "runTest")
    public ResponseEntity<Report> runTest(@RequestBody() TestRunRequest test) {
        return ResponseEntity.status(HttpStatus.OK).body(testService.testRun(test));
    }

    @GetMapping(value = "/{testName}")
    public ResponseEntity<Report> runTest(@PathVariable String testName) {
        return ResponseEntity.status(HttpStatus.OK).body(testService.findReport(testName));
    }
}
