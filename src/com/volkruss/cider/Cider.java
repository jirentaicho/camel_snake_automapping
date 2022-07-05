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
	

	/**
	 * 
	 * キャメルケースで宣言されているモデルからスネークケースのモデルを取得します
	 * 
	 * @param <T>
	 * @param <U>
	 * @param to
	 * @param from
	 * @return
	 */
	public <T,U> T toSnakeModel(Class<T> to, U from, String ...exclusions) {
		return this.getModelWithConvertLogic(to, from, this.converter::toSnake, exclusions);
	}
	
	/**
	 * 
	 * スネークケースで宣言されているモデルからキャメルケースのモデルを取得します
	 * 
	 * @param <T>
	 * @param <U>
	 * @param to
	 * @param from
	 * @return
	 */
	public <T,U> T toCamelModel(Class<T> to, U from, String ...exclusions) {
		return this.getModelWithConvertLogic(to, from, this.converter::toCamel, exclusions);
	}
	
	
	public <T,U> T toModel(Class<T> to, U from, String ...exclusions) {
		return this.getModelWithConvertLogic(to, from, i -> i, exclusions);
	}
	
	
	
	private <T,U> T getModelWithConvertLogic(Class<T> to ,U from, UnaryOperator<String> func,String ...exclusions) {
		T ins = null;
		try {
			Constructor<T> con = to.getDeclaredConstructor();
			ins = (T)con.newInstance();
			// privateも含めて全て取得する
			for(Field field : from.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				// fromがsample_data(スネークケース)の形になっているのでsampleData(キャメルケース)に変換する
				String toName = func.apply(field.getName());		
				//　チェックする
				if(!this.check(to.getDeclaredFields(),toName,field.getName(),exclusions)) {
					continue;
				}
				// マッピング先のクラスの変数を取得する
				Field toField = to.getDeclaredField(toName);
				toField.setAccessible(true);
				// マッピング先のクラスに、元のフィールドの値を設定する
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
		
		// フィールド未存在チェック
		if(!toFieldStrs.contains(toName)) {
			return false;
		}
		
		// 除外項目チェック
		if(Arrays.asList(exclusions).contains(fromName)) {
			return false;
		}
		return true;
	}
	
}
