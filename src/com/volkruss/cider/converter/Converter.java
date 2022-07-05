package com.volkruss.cider.converter;

public final class Converter {
	
	public final String toCamel(final String snake) {
		CamelConverter camelConverter = new CamelConverter();
		return camelConverter.getCamel(snake);
	}
	
	public final String toSnake(final String camel) {
		SnakeConverter converter = new SnakeConverter();
		return converter.getSnake(camel);
	}

}
