package com.power.springbootapi.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"postcode", "name"})})
public class PostcodeInfo {
    private @Id
    @GeneratedValue
    Long id;

    @Column(length = 4, nullable = false)
    @Length(min = 4, max = 4)
    private String postcode;

    @Column(length = 256, nullable = false)
    @Length(min = 2, max = 256)
    private String name;
}
