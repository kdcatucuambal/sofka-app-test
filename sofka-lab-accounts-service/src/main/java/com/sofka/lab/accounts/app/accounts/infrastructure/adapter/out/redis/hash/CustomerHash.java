package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.hash;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Customer")
@TypeAlias("CustomerHash")
public record CustomerHash(
        @Id
        @Indexed
        Long id,

        String name,

        @Indexed
        String identification,

        Boolean status
) {
}
