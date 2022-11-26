package com.example.questionnaire.dao;

import com.example.questionnaire.entity.Admin;
import com.example.questionnaire.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    AdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public Admin checkOne(AdminModel adminModel) throws DataAccessException {
        try {
            String sql = ""
                    + "SELECT"
                    + " name,"
                    + " password"
                    + " FROM"
                    + " admins"
                    + " WHERE"
                    + " name = ?"
                    + " AND"
                    + " password = ?";
            RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<Admin>(Admin.class);
            Admin admin = jdbcTemplate.queryForObject(sql, rowMapper, adminModel.getName(), adminModel.getPassword());
    
            System.out.println("nullじゃないよ");
            return admin;
        } catch (Exception exception) {
            System.out.println("nullだよ");
            return null;
        }
    }
}
