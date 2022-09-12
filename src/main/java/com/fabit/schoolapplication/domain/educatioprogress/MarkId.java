package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class MarkId {
    long value;

    private MarkId(long value) {
        this.value = value;
    }

    public static MarkId of(long value) {
        return new MarkId(value);
    }
}
