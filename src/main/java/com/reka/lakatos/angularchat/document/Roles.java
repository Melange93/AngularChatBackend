package com.reka.lakatos.angularchat.document;

public enum Roles {
    USER("user");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
