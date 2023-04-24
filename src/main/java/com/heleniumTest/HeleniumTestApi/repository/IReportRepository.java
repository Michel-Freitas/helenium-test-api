package com.heleniumTest.HeleniumTestApi.repository;

import com.heleniumTest.HeleniumTestApi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportRepository extends JpaRepository<Report, String> {
}
