package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Report;
import com.dolphin.report_book.entity.User;
import com.dolphin.report_book.entity.dto.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReportService {
    boolean insert(Report report);

    boolean deleteById(Integer id);

    Report getById(Integer id);

    List<Report> listReports();

    List<Report> listReports(Report report);

    List<Report> listReportsByCollegeId(Integer id, Integer status);

    int countReports(Report report);

    boolean update(Report report);

    ResponseResult publishReport(String title, String info, String reporterInfo, MultipartFile file, MultipartFile reportFile, User loginUser);

    ResponseResult editReport(Integer id, String title, String info, String reporterInfo, MultipartFile file, MultipartFile reportFile, User loginUser);

    ResponseResult dealReport(Integer id, int status, String checkInfo);

    String generateTitle(String title);

    String generateReport(String info);

    String generateReporter(String info);
}
