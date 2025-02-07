package mindera.porto.moveWell.service;

import mindera.porto.moveWell.dto.VideoCreateDto;
import mindera.porto.moveWell.dto.VideoDeleteDto;
import mindera.porto.moveWell.dto.VideoReadDto;
import mindera.porto.moveWell.entity.Category;
import mindera.porto.moveWell.entity.RoleType;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.entity.Video;
import mindera.porto.moveWell.repository.UserRepository;
import mindera.porto.moveWell.repository.VideoRepository;
import mindera.porto.moveWell.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
    }

    public List<VideoReadDto> getVideos() {
        return this.videoRepository.findAll()
                .stream()
                .map(VideoMapper:: fromVideoToVideoReadDto)
                .toList();
    }

    public List<VideoReadDto> getLastVideos() {
        return this.videoRepository.findLast20Videos()
                .stream()
                .map(VideoMapper:: fromVideoToVideoReadDto)
                .toList();
    }

    public List<VideoReadDto> getVideosByCategory(Long categoryId) {
        return this.videoRepository.findByCategoryId(categoryId)
                .stream()
                .map(VideoMapper:: fromVideoToVideoReadDto)
                .toList();
    }

    public VideoReadDto addNewVideo(VideoCreateDto videoCreateDto) {
        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
                videoCreateDto.getUsername(),
                videoCreateDto.getPassword());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getRole().getRoleType() == RoleType.PHYSIOTHERAPIST){

                try {
                    Video video = VideoMapper.fromVideoCreateDtoToVideo(videoCreateDto);
                    video.setUserCreator(user);
                    Video videoSaved = videoRepository.save(video);
                    return VideoMapper.fromVideoToVideoReadDto(videoSaved);
                } catch (Exception e) {
                    throw new IllegalStateException("Video is duplicated");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only physiotherapists can add videos.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials. Please check your username and password.");
        }
    }

//    public void deleteOwnVideo(Long videoId, VideoDeleteDto videoDeleteDto) {
//        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
//                videoDeleteDto.getUsername(),
//                videoDeleteDto.getPassword()
//        );
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//
//            Optional<Video> videoOptional = videoRepository.findById(videoId);
//
//            if (videoOptional.isPresent()){
//                Video video = videoOptional.get();
//
//                if ()
//
//            }
//    }
}
