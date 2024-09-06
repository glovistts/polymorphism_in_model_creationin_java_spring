package com.polymorphisminmodelcreationinjavaspring.enums;

import lombok.Getter;

@Getter
public enum RecordStatus {
    PUBLISHED("PUBLISHED"),
    DELETED("DELETED");

    private final String status;

    RecordStatus(String status) {
        this.status = status;
    }
}
