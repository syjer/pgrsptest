package com.syjer.pgrsptest.pgrsptest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    private final DataManager dataManager;

    public DataController(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @GetMapping("/option/list")
    public List<KeyValue> getAll() {
        return dataManager.findAll();
    }


}
