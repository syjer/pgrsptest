package com.syjer.pgrsptest.pgrsptest;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DataManager {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    public DataManager(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<KeyValue> findAll() {
        return jdbcTemplate.query("select * from application_option", (rs, i) ->
            new KeyValue(rs.getLong("ao_id"), rs.getString("ao_key"), rs.getString("ao_value"))
        );
    }
}
