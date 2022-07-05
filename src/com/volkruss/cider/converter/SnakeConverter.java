package com.volkruss.cider.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SnakeConverter {
	/**
	 * キャメルケースの文字列をスネークケースにして返します
	 * sampleTextStr →sample_text_str
	 * 
	 * @param name
	 * @return
	 */
	public final String getSnake(final String name) {
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(name);
		if(!matcher.find()) {
			return name;
		}
		int index = name.indexOf(matcher.group());
		String newName = 
				name.substring(0,index) 
				+ "_"
				+ name.substring(index,index + 1).toLowerCase() 
				+ name.substring(index+1);
		return getSnake(newName);
	}
}
