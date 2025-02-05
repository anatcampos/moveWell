package mindera.porto.moveWell.service;

import mindera.porto.moveWell.dto.CategoryCreateDto;
import mindera.porto.moveWell.dto.CategoryDeleteDto;
import mindera.porto.moveWell.dto.CategoryReadDto;
import mindera.porto.moveWell.entity.Category;
import mindera.porto.moveWell.entity.RoleType;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.mapper.CategoryMapper;
import mindera.porto.moveWell.mapper.UserMapper;
import mindera.porto.moveWell.repository.CategoryRepository;
import mindera.porto.moveWell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<CategoryReadDto> getCategories() {
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::fromCategoryToCategoryReadDto)
                .toList();
    }

    public CategoryReadDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("Category does not exist"));
        return CategoryMapper.fromCategoryToCategoryReadDto(category);
    }

    public CategoryReadDto addNewCategory(CategoryCreateDto categoryCreateDto) {
        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
                categoryCreateDto.getUsername(),
                categoryCreateDto.getPassword());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getRole().getRoleType() == RoleType.PHYSIOTHERAPIST) {

                try {
                    Category category = CategoryMapper.fromCategoryCreateDtoToCategory(categoryCreateDto);
                    Category categorySaved = categoryRepository.save(category);
                    return CategoryMapper.fromCategoryToCategoryReadDto(categorySaved);
                } catch (Exception e){
                    throw new IllegalStateException("Category is duplicated");
                }

            } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only physiotherapists can add categories.");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials. Please check your username and password.");
        }
    }

    public void deleteCategory(Long categoryId, CategoryDeleteDto categoryDeleteDto) {
        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
                categoryDeleteDto.getUsername(),
                categoryDeleteDto.getPassword()
        );

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getRole().getRoleType() == RoleType.PHYSIOTHERAPIST) {
                Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

                if (categoryOptional.isPresent()) {
                    categoryRepository.delete(categoryOptional.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.");
                }

            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only physiotherapists can delete categories.");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials. Please check your username and password.");
        }
    }
}
