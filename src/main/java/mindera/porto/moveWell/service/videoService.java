package mindera.porto.moveWell.service;

import mindera.porto.moveWell.entity.Video;
import mindera.porto.moveWell.repository.videoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class videoService {
private final videoRepository videoRepository;

    public videoService(mindera.porto.moveWell.repository.videoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }
    public List<VideoReadDto> getVideos )={
        return this.videoRepository.findAll()
                .stream()
                .map(VideoMapper:: fromVideoToVideoDto)
                .toList();
    }
    public VideoReadDto getVideo (Long videoId){
        Video video= videoRepository.findById(videoId).orElseThrow()-> new
    }
}
//
//    public CategoryReadDto getCategory(Long categoryId) {
//        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("Category does not exist"));
//        return CategoryMapper.fromCategoryToCategoryReadDto(category);
//    }
//    public CategoryReadDto addNewCategory(CategoryCreateDto categoryCreateDto) {
//        try {
//            Category category = CategoryMapper.fromCategoryCreateDtoToCategory(categoryCreateDto);
//            Category categorySaved = categoryRepository.save(category);
//            return CategoryMapper.fromCategoryToCategoryReadDto(categorySaved);
//        } catch (Exception e) {
//            throw new IllegalStateException("Category is duplicated");
//        }
//    }
//    public void deleteCategory(Long categoryId, CategoryDeleteDto categoryDeleteDto) {
//        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
//                categoryDeleteDto.getUsername(),
//                categoryDeleteDto.getPassword()
//        );
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (user.getRole().getRoleType() == RoleType.PHYSIOTHERAPIST) {
//                Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
//                if (categoryOptional.isPresent()) {
//                    categoryRepository.delete(categoryOptional.get());
//                } else {
//                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.");
//                }
//            } else {
//                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only physiotherapists can delete categories.");
//            }
//        } else {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials. Please check your username and password.");
//        }
