package com.heleniumTest.HeleniumTestApi.controller;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@RestController
@RequestMapping("test")
@CrossOrigin(origins = "*")
public class TestController {
    @GetMapping(value = "runTest")
    public ResponseEntity<String> runTest() {
        String executionProject = "C:\\Users\\michel_freitas\\Documents\\testHealing\\template_project";
        Path executionProjectPath = Paths.get(executionProject);
        String testRunScript = "-Dtest=Teste1";

        DefaultInvocationRequest invocationResult = new DefaultInvocationRequest();
        invocationResult.setPomFile(new File(executionProjectPath.resolve("pom.xml").toString()));
        invocationResult.setGoals(Arrays.asList(testRunScript, "test"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("C:\\maven"));

        try {
            invoker.execute(invocationResult);
        } catch (MavenInvocationException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Rodar Teste");
    }
}
