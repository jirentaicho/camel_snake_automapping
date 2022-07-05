package com.volkruss.cider;

import com.volkruss.cider.model.ItemDto;
import com.volkruss.cider.model.ItemEntity;

public class Main {

	public static void main(String[] args) {
		
		ItemEntity entity = new ItemEntity();
		entity.item_id = 1;
		entity.item_name = "ProductA";
		
		Cider cider = new Cider();
		ItemDto dto = cider.toCamelModel(entity, ItemDto.class);
		
		System.out.println(String.valueOf(dto.itemId) + ":" + dto.itemName);
		// 1:ProductA
	}

}
