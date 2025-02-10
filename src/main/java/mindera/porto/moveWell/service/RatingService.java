package mindera.porto.moveWell.service;

import mindera.porto.moveWell.dto.RatingCreateDto;
import mindera.porto.moveWell.dto.RatingReadDto;
import mindera.porto.moveWell.entity.Rating;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.entity.Video;
import mindera.porto.moveWell.mapper.RatingMapper;
import mindera.porto.moveWell.repository.RatingRepository;
import mindera.porto.moveWell.repository.UserRepository;
import mindera.porto.moveWell.repository.VideoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, VideoRepository videoRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    public Double getRatingsByVideoId(Long videoId) {
        Optional<Video> videoOptional = videoRepository.findById(videoId);
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
            return ratingRepository.getAverageRatingByVideo(videoId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This video doesn't exist");
        }
    }

    public Double getRatingsByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ratingRepository.getAverageRatingByUser(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public RatingReadDto addNewRating(RatingCreateDto ratingCreateDto) {
        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
                ratingCreateDto.getUsername(),
                ratingCreateDto.getPassword()
        );
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Video> videoOptional = videoRepository.findById(ratingCreateDto.getVideoId());
            if (videoOptional.isPresent()) {
                Video video = videoOptional.get();
                Rating rating = RatingMapper.fromRatingCreateDtoToRating(ratingCreateDto);
                rating.setUser(user);
                rating.setVideo(video);
                Rating ratingSaved = ratingRepository.save(rating);
                return RatingMapper.fromRatingToRatingReadDto(ratingSaved);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }
}
