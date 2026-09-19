package com.shiptrack.auth;
public record AuthResponse(String token,String name,String email,String role) {}