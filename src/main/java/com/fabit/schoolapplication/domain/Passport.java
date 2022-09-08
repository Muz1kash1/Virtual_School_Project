package com.fabit.schoolapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
@Value
public record Passport(String serial, String number) {
}
