package com.fishep.user.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenCheckResponse {
    public Integer version;
    public String guard;
    public String userType;
    public Long userId;
    public String userName;
}
