package com.fishep.user.application.dto;

import com.fishep.common.exception.NullException;
import com.fishep.common.exception.TypeException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.type.UserName;
import lombok.Data;

@Data
public class LoginDTO {

    public enum IdentityType {
        UserName, Email, PhoneNumber
    }

    public enum TokenType {
        Password, Code
    }

    public String identity;

    public IdentityType identityType;

    public String token;

    public TokenType tokenType;

    public LoginDTO(String identity, String token) {
        this.checkArgument(identity, token);

        this.identity = identity;
        this.identityType = this.inferIdentityType();
        this.token = token;
        this.tokenType = this.inferTokenType();
    }

    public LoginDTO(String identity, String token, TokenType tokenType) {
        this.checkArgument(identity, token);

        this.identity = identity;
        this.identityType = this.inferIdentityType();
        this.token = token;
        this.tokenType = tokenType;
    }

    public LoginDTO(String identity, IdentityType identityType, String token) {
        this.checkArgument(identity, token);

        this.identity = identity;
        this.identityType = identityType;
        this.token = token;
        this.tokenType = this.inferTokenType();
    }

    public LoginDTO(String identity, IdentityType identityType, String token, TokenType tokenType) {
        this.checkArgument(identity, token);

        this.identity = identity;
        this.identityType = identityType;
        this.token = token;
        this.tokenType = tokenType;
    }

    private void checkArgument(String identity, String token) {
        if (identity == null || identity.isEmpty() || token == null || token.isEmpty()) {
            throw new NullException("Can not be null, Identity: " + identity + ", token: " + token);
        }
    }

    private IdentityType inferIdentityType() {
        if (identity.matches(UserName.regex)) {
            identityType = IdentityType.UserName;
        } else if (identity.matches(Email.regex)) {
            identityType = IdentityType.Email;
        } else if (identity.matches(PhoneNumber.regex)) {
            identityType = IdentityType.PhoneNumber;
        } else {
            throw new TypeException("Unable to infer the type of Identity, Identity: " + identity);
        }

        return identityType;
    }

    private TokenType inferTokenType() {
        if (token.length() == 4) {
            tokenType = TokenType.Code;
        } else {
            tokenType = TokenType.Password;
        }

        return tokenType;
    }
}
