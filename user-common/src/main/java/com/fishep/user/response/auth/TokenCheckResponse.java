package com.fishep.user.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenCheckResponse {
    public Boolean flag;

    public Long userId;
}
