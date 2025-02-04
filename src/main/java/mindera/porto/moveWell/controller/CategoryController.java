package mindera.porto.moveWell.controller;


import mindera.porto.moveWell.dto.*;
import mindera.porto.moveWell.entity.Category;
import mindera.porto.moveWell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryReadDto>> getCategories(){

        try {
            List<CategoryReadDto> categories = this.categoryService.getCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{categoryId}")
    public ResponseEntity<CategoryReadDto> getCategory(@PathVariable("categoryId") Long categoryId) {

        try {
            CategoryReadDto category = this.categoryService.getCategory(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public CategoryReadDto addNewCategory(@RequestBody CategoryCreateDto categoryCreateDto){
        return categoryService.addNewCategory(categoryCreateDto);
    }


    @DeleteMapping(path = "{categoryId}")
    public ResponseEntity<String> deleteCategory (@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDeleteDto categoryDeleteDto){
        categoryService.deleteCategory(categoryId, categoryDeleteDto);
        return ResponseEntity.ok("Category was successfully deleted.");
    }



}
