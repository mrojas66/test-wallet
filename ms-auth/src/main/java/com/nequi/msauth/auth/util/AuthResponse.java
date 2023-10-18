package com.nequi.msauth.auth.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    String token; 
}
