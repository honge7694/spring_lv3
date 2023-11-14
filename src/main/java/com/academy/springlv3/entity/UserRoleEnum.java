package com.academy.springlv3.entity;

public enum UserRoleEnum {
    STAFF(Authority.STAFF),  // STAFF 권한
    MANAGE(Authority.MANAGER);  // MANAGER 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String STAFF = "STAFF";
        public static final String MANAGER = "MANAGER";
    }
}