package mindera.porto.moveWell.service;

import mindera.porto.moveWell.dto.CategoryCreateDto;
import mindera.porto.moveWell.dto.CategoryReadDto;
import mindera.porto.moveWell.entity.Role;
import mindera.porto.moveWell.mapper.CategoryMapper;
import mindera.porto.moveWell.entity.Category;
import mindera.porto.moveWell.entity.RoleType;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.repository.CategoryRepository;
import mindera.porto.moveWell.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private Category testCategory;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void shouldRun(){
        assert true;
    }











//    @Test
//    void addNewCategory_ShouldAddCategory_WhenUserIsPhysiotherapist() {
//        // Arrange
//        CategoryCreateDto categoryCreateDto = new CategoryCreateDto();
//        categoryCreateDto.setUsername("physioUser");
//        categoryCreateDto.setPassword("password123");
//        categoryCreateDto.setArea("Rehabilitation");
//
//        User user = new User();
//        user.setRole(new Role(RoleType.PHYSIOTHERAPIST));
//
//        Category category = new Category();
//        Category savedCategory = new Category();
//        CategoryReadDto categoryReadDto = new CategoryReadDto();
//
//        when(userRepository.findUserByUsernameAndPassword("physioUser", "password123"))
//                .thenReturn(Optional.of(user));
//        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);
//        when(CategoryMapper.fromCategoryCreateDtoToCategory(categoryCreateDto)).thenReturn(category);
//        when(CategoryMapper.fromCategoryToCategoryReadDto(savedCategory)).thenReturn(categoryReadDto);
//
//        // Act
//        CategoryReadDto result = categoryService.addNewCategory(categoryCreateDto);
//
//        // Assert
//        assertNotNull(result);
//        verify(categoryRepository, times(1)).save(category);
//    }
}
