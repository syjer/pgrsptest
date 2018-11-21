package com.syjer.pgrsptest.pgrsptest;

public class KeyValue {

    private final long id;
    private final String key;
    private final String value;

    public KeyValue(long id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
