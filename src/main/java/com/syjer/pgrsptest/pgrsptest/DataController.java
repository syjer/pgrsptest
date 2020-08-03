package com.syjer.pgrsptest.pgrsptest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    private final DataManager dataManager;
    private final JdbcTemplate jdbcTemplate;

    public DataController(DataManager dataManager, JdbcTemplate jdbcTemplate) {
        this.dataManager = dataManager;
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/option/list")
    public List<KeyValue> getAll() {
        return dataManager.findAll();
    }

    @GetMapping("/option/list-direct")
    public List<KeyValue> getAllDirect() {
        return jdbcTemplate.query("select * from application_option", (rs, i) ->
                new KeyValue(rs.getLong("ao_id"), rs.getString("ao_key"), rs.getString("ao_value"))
        );
    }


}
