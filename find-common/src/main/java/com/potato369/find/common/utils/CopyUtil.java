package com.potato369.find.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class CopyUtil {
	
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
    // 去掉long类型的重复元素
	public static List<Long> removeLongDuplicate(List<Long> list) {

		HashSet<Long> h = new HashSet<>(list);

		list.clear();

		list.addAll(h);
		
		return list;
	}
	
	// 去掉string类型的重复元素
	public static List<String> removeStringDuplicate(List<String> list) {

		HashSet<String> h = new HashSet<>(list);

		list.clear();

		list.addAll(h);
		
		return list;
	}
}
