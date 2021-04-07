package com.potato369.find.common.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class MunicipalityConstant {
	
	private List<String> municipalityList = new ArrayList<>();
	
	public MunicipalityConstant() {
		this.municipalityList.add(0, "北京市");
		this.municipalityList.add(1, "上海市");
		this.municipalityList.add(2, "天津市");
		this.municipalityList.add(3, "重庆市");
	}
}
