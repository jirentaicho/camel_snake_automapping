package com.volkruss.cider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import com.volkruss.cider.converter.Converter;

public class Cider {
	
	private final Converter converter;

	public Cider() {
		this.converter = new Converter();
	}
	
	public <T> T toSnakeModel(Object source, Class<T> clazz, String ...exclusions) {
		return this.getModelWithConvertLogic(clazz, source, this.converter::toSnake, exclusions);
	}
	
	public <T> T toCamelModel(Object source, Class<T> clazz, String ...exclusions) {
		return this.getModelWithConvertLogic(clazz, source, this.converter::toCamel, exclusions);
	}
	
	public <T> T toModel(Object source,Class<T> clazz, String ...exclusions) {
		return this.getModelWithConvertLogic(clazz, source, i -> i, exclusions);
	}
	
	
	private <T,U> T getModelWithConvertLogic(Class<T> to ,U from, UnaryOperator<String> func,String ...exclusions) {
		T ins = null;
		try {
			Constructor<T> con = to.getDeclaredConstructor();
			ins = (T)con.newInstance();
			for(Field field : from.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				String toName = func.apply(field.getName());		
				
				if(!this.check(to.getDeclaredFields(),toName,field.getName(),exclusions)) {
					continue;
				}
				
				Field toField = to.getDeclaredField(toName);
				toField.setAccessible(true);
				toField.set(ins, field.get(from));
			}	
		} catch (Exception e) { System.out.println(e); }
		return ins;
	}
	
	
	private boolean check(Field[] toFields,String toName, String fromName, String ...exclusions) {
		
		List<String> toFieldStrs = new ArrayList<>();
		for(Field field : toFields) {
			toFieldStrs.add(field.getName());
		}
		
		if(!toFieldStrs.contains(toName)) {
			return false;
		}
		
		if(Arrays.asList(exclusions).contains(fromName)) {
			return false;
		}
		return true;
	}

}
