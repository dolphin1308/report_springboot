package com.dolphin.report_book.controller;

import com.dolphin.report_book.entity.Appointment;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
@Slf4j
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    /**
     * 导出会议的签到表
     */
    @GetMapping("/exportAppointment")
    public void exportAppointment(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
        appointmentService.exportAppointment(id, response);
    }

    /**
     * 导入学生签到信息
     */
    @PostMapping("/importAppointments")
    public ResponseResult importAppointments(MultipartFile file) {
        ResponseResult result = new ResponseResult();
        String fileName = file.getOriginalFilename();
        log.info("接收到文件：{}", fileName);
        String pattern = "^《[^》]+》-会议签到表\\.xls$";
        // 验证文件名是否合法，格式：《会议名称》-会议签到表.xls
        if (!Pattern.matches(pattern, file.getOriginalFilename())) {
            result.setCode(301);
            result.setMessage("上传文件的格式（与导出文件名相同）必须为：《报告名称》-会议签到表.xls");
            return result;
        }
        return appointmentService.importAppointments(file);
    }

    /**
     * 更新签到状态
     */
    @PostMapping("/updateStatus")
    public ResponseResult updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        // 构建预约对象
        Appointment appointment = Appointment.builder()
                .id(id).present(status)
                .build();
        ResponseResult result = new ResponseResult();
        if (appointmentService.update(appointment)) {
            result.setCode(200);
            result.setMessage("更新签到状态成功");
        } else {
            result.setCode(500);
            result.setMessage("服务器错误");
        }
        return result;
    }
}
