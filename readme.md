Converts skilly like cider.

version 1.0

Automatic mapping of beans associated with table definitions to Dto, etc.  
Mapping is possible even in cases where variable names differ between the Camel and Snake cases.  
Models with the same field name can also be mapped.

# Feature

* Maps the value of the camel case variable to the value of the snake case variable
* Maps the value of a variable in the snake case to the value of a variable in the camel case 
* You can also exclude fields you do not want mapped by specifying which fields you do not want mapped

# caution

version 1.0 cannot handle models with constructors that require arguments.


# How to use

```java
ItemEntity entity = new ItemEntity();
entity.item_id = 1;
entity.item_name = "ProductA";

Cider cider = new Cider();
ItemDto dto = cider.toCamelModel(entity, ItemDto.class);

System.out.println(String.valueOf(dto.itemId) + ":" + dto.itemName);
```

result

> 1:ProductA

If there are fields you want to exclude, specify them after the third argument

```java
ItemEntity entity = new ItemEntity();
entity.item_id = 1;
entity.item_name = "ProductA";

Cider cider = new Cider();
ItemDto dto = cider.toCamelModel(entity, ItemDto.class,"item_id");

System.out.println(String.valueOf(dto.itemId) + ":" + dto.itemName);
```

result

> 0:ProductA



