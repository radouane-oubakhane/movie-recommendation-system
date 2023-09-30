package com.radouaneoubakhane.apigateway.constants;

public interface SecurityConstants {

    public static final String JWT_KEY = "0x0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF\n";
    public static final String JWT_HEADER = "Authorization";
    public static final long EXPIRATION_TIME = 8 * 60 * 60 * 1000; // 8 hours
}
