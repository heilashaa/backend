package com.haapp.formicary.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenPayload {

    private Long userId;
    private long expiration;

    public TokenPayload(final Long userId, final long expiration) {
        this.userId = userId;
        this.expiration = expiration;
    }
}
