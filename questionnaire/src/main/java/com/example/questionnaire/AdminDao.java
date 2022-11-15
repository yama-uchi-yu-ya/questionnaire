package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    AdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Admin> adminList() {
        String sql = ""
                + "SELECT"
                + " name,"
                + " password"
                + " FROM"
                + " admins";
        RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<Admin>(Admin.class);
        List<Admin> adminList = jdbcTemplate.query(sql, rowMapper);

        return adminList;
    }
}
