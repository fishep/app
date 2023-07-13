package com.fishep.common.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author fly.fei
 * @Date 2023/7/7 11:14
 * @Desc
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locale implements Serializable {
    private String en_US;
    private String zh_CN;
}
