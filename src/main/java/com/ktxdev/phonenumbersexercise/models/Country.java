package com.ktxdev.phonenumbersexercise.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    private String code;

    private String name;

    private String phoneValidationRegex;
}
