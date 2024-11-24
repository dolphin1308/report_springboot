package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Admin;

import java.util.List;

public interface AdminService {

    public boolean insert(Admin admin);

    public boolean deleteById(Integer id);

    public Admin getById(Integer id);

    public Admin getByNo(String no);

    public List<Admin> listAdmins();

    public List<Admin> listAdmins(Admin admin);

    public int countAdmins(Admin admin);

    public boolean update(Admin admin);
}
