package com.potato369.find.common.constants;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class ConstellationConstant {

	private List<String> constellationList = new ArrayList<>();

	public ConstellationConstant() {
		this.constellationList.add(0, "水瓶座");// 01.20-02.18
		this.constellationList.add(1, "双鱼座");// 02.19-03.20
		this.constellationList.add(2, "白羊座");// 03.21-04.19
		this.constellationList.add(3, "金牛座");// 04.20-05.20
		this.constellationList.add(4, "双子座");// 05.21-06.21
		this.constellationList.add(5, "巨蟹座");// 06.22-07.22
		this.constellationList.add(6, "狮子座");// 07.23-08.22
		this.constellationList.add(7, "处女座");// 08.23-09.22
		this.constellationList.add(8, "天秤座");// 09.23-10.23
		this.constellationList.add(9, "天蝎座");// 10.24-11.22
		this.constellationList.add(10, "射手座");// 11.23-12.21
		this.constellationList.add(11, "摩羯座");// 12.21-01.19
	}
}
