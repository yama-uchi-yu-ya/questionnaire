package com.example.questionnaire;

import com.example.questionnaire.QuestionController.QueryAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryAnswerDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    QueryAnswerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(QueryAnswer queryAnswer) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(queryAnswer);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("query_answer");
        insert.execute(param);
    }
    public List<QueryAnswer> findAll() {
        String query = "SELECT * FROM query_answer";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<QueryAnswer>  queryAnswers = result.stream()
                .map((Map<String, Object> row) -> new QueryAnswer(
                        row.get("id").toString(),
                        row.get("like_meat").toString(),
                        row.get("like_veg").toString(),
                        row.get("like_idol").toString()))
                .toList();

        return queryAnswers;
    }
}
