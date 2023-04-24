package com.heleniumTest.HeleniumTestApi.service.implementation;

import com.google.gson.Gson;
import com.heleniumTest.HeleniumTestApi.dto.request.TestRunRequest;
import com.heleniumTest.HeleniumTestApi.model.Report;
import com.heleniumTest.HeleniumTestApi.model.ReportElement;
import com.heleniumTest.HeleniumTestApi.model.ReportElementRecord;
import com.heleniumTest.HeleniumTestApi.model.TestRun;
import com.heleniumTest.HeleniumTestApi.repository.IReportRepository;
import com.heleniumTest.HeleniumTestApi.service.ITestService;
import org.apache.commons.io.FileUtils;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

@Service
public class TestService implements ITestService {

    @Autowired
    private IReportRepository reportRepository;
    private final String cloneProjectDirectory;
    private final String testPath;
    private final String mavenDirectory;

    public TestService() {
        // TODO: Remover o nome do projeto pois ele sera criado pela aplicação
        // TODO: Deixar somente o caminho raiz para colocar o projeto EXEMPLO (C:/Users/michel_freitas/Documents/testHealing)
        cloneProjectDirectory = PathResolve("C:/Users/michel_freitas/Documents/testHealing/template_project");
        mavenDirectory = PathResolve("C:/maven");
        testPath = PathResolve("/src/test/java/com/templateProject/template_project");
    }

    @Override
    public Report testRun(TestRunRequest test) {
        TestRun testRun = new TestRun(test.getTestName(), test.getSeleniumCode());
        testRun.validateTest();
        testRun.assembleTestScript();
        Path executionProjectPath = Paths.get(cloneProjectDirectory);

        if (!Files.exists(executionProjectPath)) {
            // TODO: Fazer a copia do projeto template
        }

        Path testFilePath = Paths.get(cloneProjectDirectory + PathResolve(testPath));
        Path testTargetLocation = testFilePath.resolve(testRun.getTestNameWithExtension());

        setUpTestFile(testRun.getTestScript(), testTargetLocation);

        String testRunScript = "-Dtest=" + testRun.getTestName();

        DefaultInvocationRequest invocationResult = new DefaultInvocationRequest();
        invocationResult.setPomFile(new File(executionProjectPath.resolve("pom.xml").toString()));
        invocationResult.setGoals(Arrays.asList(testRunScript, "test"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(mavenDirectory));

        try {
            invoker.execute(invocationResult);
        } catch (MavenInvocationException e) {
            throw new RuntimeException(e);
        }

        return findReport(testRun.getTestName());
    }

    private void copyDir(String sourceDir, String targetDir) {
        try {
            File sourceDirectory = new File(sourceDir);
            File targetDirectory = new File(targetDir);

            FileUtils.copyDirectory(sourceDirectory, targetDirectory);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    private String PathResolve(String path) {
        return path.replace('/', File.separatorChar);
    }
    private void setUpTestFile(String testScript, Path testTargetLocation) {
        try {
            try (FileWriter fileWriter = new FileWriter(testTargetLocation.toString())) {
                fileWriter.write(testScript);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Report findReport(String testName) {
        String testClassName = "com.templateProject.template_project." + testName;
        Predicate<ReportElementRecord> filterReportElement = r -> r.getClassName().equalsIgnoreCase(testClassName);

        Gson gson = new Gson();
        List<Report> reportsList = reportRepository.findAll();
        Report report = new Report();

        reportsList.forEach(item -> {
            ReportElement reportElement = gson.fromJson(item.getElements(), ReportElement.class);
            List<ReportElementRecord> reportElementRecordList = reportElement.getRecords().stream()
                    .filter(filterReportElement).toList();
            if (reportElementRecordList.size() > 0) {
                report.setCreateDate(item.getCreateDate());
                report.setElements(item.getElements());
                report.setUid(item.getUid());
                report.setMappedElements(reportElement);
            }
        });

        return report;
    }
}
