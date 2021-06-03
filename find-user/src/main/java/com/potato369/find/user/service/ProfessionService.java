package com.potato369.find.user.service;

import com.potato369.find.mbg.model.Industrys;
import com.potato369.find.mbg.model.Professions;

import java.util.List;

public interface ProfessionService {

    List<Industrys> getAllUnDeleteIndustrys();

    List<Professions> getAllUndeleteProfessions();

    List<Professions> getProfessionsByIndustrysId(Long id);
}
