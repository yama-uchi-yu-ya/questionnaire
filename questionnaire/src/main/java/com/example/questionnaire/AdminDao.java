package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.zip.DataFormatException;

public class AdminDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Admin findUser(String name) throws DataAccessException {
        String sql = ""
                + "SELECT"
                + " name"
                + " FROM"
                + " admins"
                + " WHERE"
                + " admin_id = ?";

        RowMapper<Admin> rowMapper = new BeanPropertyRowMapper<Admin>(Admin.class);
        Admin admin = jdbcTemplate.queryForObject(sql, rowMapper, name);

        return admin;
    }
}


