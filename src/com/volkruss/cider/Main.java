package com.volkruss.cider;

import com.volkruss.cider.converter.Converter;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Cider cider = new Cider();
		//String name = cider.getSnake("sampleTextStr");
		Converter converter = new Converter();
		
		String name = converter.toCamel("sample_text_str");
		String ba = converter.toSnake("sampleTextStr");
		
		System.out.println(name);
		System.out.println(ba);
		
	}

}
