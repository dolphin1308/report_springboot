package com.dolphin.report_book.service.impl;

import com.dolphin.report_book.entity.Admin;
import com.dolphin.report_book.mapper.AdminMapper;
import com.dolphin.report_book.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    public boolean insert(Admin admin) {
        return adminMapper.insert(admin) == 1;
    }

    public boolean deleteById(Integer id) {
        return adminMapper.deleteById(id) == 1;
    }

    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    public Admin getByNo(String no) {
        return adminMapper.getByNo(no);
    }

    public List<Admin> listAdmins() {
        return adminMapper.listAdmins();
    }

    public List<Admin> listAdmins(Admin admin) {
        return adminMapper.listAdmins(admin);
    }

    public int countAdmins(Admin admin) {
        return adminMapper.countAdmins(admin);
    }

    public boolean update(Admin admin) {
        return adminMapper.update(admin) == 1;
    }
}
