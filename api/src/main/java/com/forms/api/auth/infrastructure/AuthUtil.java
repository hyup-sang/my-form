package com.forms.api.auth.infrastructure;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthUtil {
    static public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
