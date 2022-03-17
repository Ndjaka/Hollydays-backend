package com.ozone.hollidays.enums;

public enum StatusType {

    ALL_USER("ALL_USER"),
    SPECIFIC_USER("SPECIFIC_USER");

    private final String status;

    StatusType(String _status) {
        this.status = _status;
    }

    public String getStatus() {
        return this.status;
    }
}
