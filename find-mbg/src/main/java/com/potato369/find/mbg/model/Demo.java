package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Demo implements Serializable {

    private Long id;

    private Integer age;

    private String createTime;

    private String name;

    private String updateTime;

    private static final long serialVersionUID = 1L;
}