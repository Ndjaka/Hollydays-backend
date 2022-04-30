package com.ozone.hollidays.config;


public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="med@youssfi.net";
    public static final long EXPIRATION=864_000_000;
    public static final String HEADER_PREFIX="Bearer ";
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
}