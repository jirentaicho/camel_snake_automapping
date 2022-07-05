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
	 * �L�������P�[�X�Ő錾����Ă��郂�f������X�l�[�N�P�[�X�̃��f�����擾���܂�
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
	 * �X�l�[�N�P�[�X�Ő錾����Ă��郂�f������L�������P�[�X�̃��f�����擾���܂�
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
			// private���܂߂đS�Ď擾����
			for(Field field : from.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				// from��sample_data(�X�l�[�N�P�[�X)�̌`�ɂȂ��Ă���̂�sampleData(�L�������P�[�X)�ɕϊ�����
				String toName = func.apply(field.getName());		
				//�@�`�F�b�N����
				if(!this.check(to.getDeclaredFields(),toName,field.getName(),exclusions)) {
					continue;
				}
				// �}�b�s���O��̃N���X�̕ϐ����擾����
				Field toField = to.getDeclaredField(toName);
				toField.setAccessible(true);
				// �}�b�s���O��̃N���X�ɁA���̃t�B�[���h�̒l��ݒ肷��
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
		
		// �t�B�[���h�����݃`�F�b�N
		if(!toFieldStrs.contains(toName)) {
			return false;
		}
		
		// ���O���ڃ`�F�b�N
		if(Arrays.asList(exclusions).contains(fromName)) {
			return false;
		}
		return true;
	}
	
}