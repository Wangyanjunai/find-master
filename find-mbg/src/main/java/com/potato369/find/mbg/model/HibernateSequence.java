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
public class HibernateSequence implements Serializable {

    private Long nextVal;

    private static final long serialVersionUID = 1L;
}