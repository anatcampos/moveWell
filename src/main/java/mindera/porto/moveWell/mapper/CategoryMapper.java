package mindera.porto.moveWell.mapper;

import mindera.porto.moveWell.dto.CategoryCreateDto;
import mindera.porto.moveWell.dto.CategoryDeleteDto;
import mindera.porto.moveWell.dto.CategoryReadDto;
import mindera.porto.moveWell.entity.Category;

public class CategoryMapper {

    public static CategoryReadDto fromCategoryToCategoryReadDto (Category category) {
        CategoryReadDto categoryReadDto = new CategoryReadDto();
        categoryReadDto.setId(category.getId());
        categoryReadDto.setArea(category.getArea());

        return categoryReadDto;
    }

    public static Category fromCategoryCreateDtoToCategory(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setArea(categoryCreateDto.getArea());
        return category;
    }

    public static Category fromCategoryDeleteDtoToCategory(CategoryDeleteDto categoryDeleteDto){
        Category category = new Category();
        category.setId(categoryDeleteDto.getId());
        return category;
    }





}
