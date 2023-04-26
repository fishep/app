package com.fishep.user.infrastructure.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDO {
    private Long id;

    private String value;
}
