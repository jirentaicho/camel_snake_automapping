package com.volkruss.cider.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SnakeConverter {
	/**
	 * �L�������P�[�X�̕�������X�l�[�N�P�[�X�ɂ��ĕԂ��܂�
	 * sampleTextStr ��sample_text_str
	 * 
	 * @param name
	 * @return
	 */
	public final String getSnake(final String name) {
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(name);
		// �}�b�`������̂�������Δ�����
		if(!matcher.find()) {
			return name;
		}
		//�@���̕����̃C���f�b�N�X���E����_��A������������������
		int index = name.indexOf(matcher.group());
		//�@���̌��̕�����啶���ɂ���
		String newName = 
				name.substring(0,index) 
				+ "_"
				+ name.substring(index,index + 1).toLowerCase() 
				+ name.substring(index+1);
		// _���������ĕԂ�
		return getSnake(newName);
	}
}
