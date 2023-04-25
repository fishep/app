package com.fishep.user.request.auth;

import com.fishep.user.annotation.CheckUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterRequest {
    @NotNull
    @NotEmpty
    @CheckUserName
    public String name;

    @NotNull
    @NotEmpty
    @Email
    public String email;

    @NotNull
    @NotEmpty
    @Length(min = 6, max = 32)
    public String password;

    @NotNull
    @NotEmpty
    @Length(min = 6, max = 32)
    public String password_confirm;

    public Boolean passwordConfirm() {
        return password.equals(password_confirm);
    }
}
