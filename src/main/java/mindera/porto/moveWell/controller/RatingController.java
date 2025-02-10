package mindera.porto.moveWell.controller;

import mindera.porto.moveWell.dto.RatingCreateDto;
import mindera.porto.moveWell.dto.RatingReadDto;
import mindera.porto.moveWell.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/video/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public Double getRatingsByVideoId(@PathVariable Long videoId) {
        return ratingService.getRatingsByVideoId(videoId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Double getRatingsByUserId(@PathVariable Long userId) {
        return ratingService.getRatingsByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RatingReadDto addNewRating(@RequestBody RatingCreateDto ratingCreateDto) {
        return ratingService.addNewRating(ratingCreateDto);
    }
}
