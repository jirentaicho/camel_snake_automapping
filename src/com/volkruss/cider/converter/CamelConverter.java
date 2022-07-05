package com.volkruss.cider.converter;

public final class CamelConverter {

	/**
	 * スネークケースの文字列をキャメルケースにして返します<br />
	 * sample_text_str → sampleTextStr
	 * 
	 * @param name
	 * @return
	 */
	public final String getCamel(final String name) {
		int index = name.indexOf('_');
		
		if(index == -1) {
			return name;
		}
		String newName = 
				name.substring(0,index) 
				+ name.substring(index + 1,index + 2).toUpperCase() 
				+ name.substring(index + 2);
		return getCamel(newName);
	}

}
