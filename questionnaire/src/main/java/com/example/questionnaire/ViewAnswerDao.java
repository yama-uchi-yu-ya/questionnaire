package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewAnswerDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ViewAnswerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ViewAnswer> viewAnswerList() {
        String sql = ""
                + "SELECT"
                + " answer_id,"
                + " meat_id,"
                + " idol_name"
                + " FROM"
                + " answers";
        RowMapper<ViewAnswer> rowMapper = new BeanPropertyRowMapper<ViewAnswer>(ViewAnswer.class);
        List<ViewAnswer> viewAnswerList = jdbcTemplate.query(sql, rowMapper);

        return viewAnswerList;
    }

    public List<ViewVegetableAnswer> viewVegetableAnswerList() {
        String sql = ""
                + "SELECT"
                + " vegetable_id,"
                + " answer_id"
                + " FROM"
                + " answer_vegetables";
        RowMapper<ViewVegetableAnswer> rowMapper = new BeanPropertyRowMapper<ViewVegetableAnswer>(ViewVegetableAnswer.class);
        List<ViewVegetableAnswer> viewVegetableAnswerList = jdbcTemplate.query(sql, rowMapper);

        return viewVegetableAnswerList;
    }
}
