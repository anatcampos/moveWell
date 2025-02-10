package mindera.porto.moveWell.controller;

import jakarta.validation.Valid;
import mindera.porto.moveWell.dto.VideoCreateDto;
import mindera.porto.moveWell.dto.VideoDeleteDto;
import mindera.porto.moveWell.dto.VideoReadDto;
import mindera.porto.moveWell.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "api/v1/videos")

public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<VideoReadDto>> getVideos(){

        try {
            List<VideoReadDto> videos = this.videoService.getVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "last")
    public ResponseEntity<List<VideoReadDto>> getLastVideos(){

        try {
            List<VideoReadDto> videos = this.videoService.getLastVideos();
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{categoryId}")
    public ResponseEntity<List<VideoReadDto>> getVideosByCategory(@PathVariable("categoryId") Long categoryId){
        try {
            List<VideoReadDto> videos = this.videoService.getVideosByCategory(categoryId);
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public VideoReadDto addNewVideo(@Valid @RequestBody VideoCreateDto videoCreateDto){
        return videoService.addNewVideo(videoCreateDto);
    }

    @DeleteMapping(path = "{videoId}")
    public ResponseEntity<String> deleteOwnVideo (@PathVariable("videoId") Long videoId,@RequestBody VideoDeleteDto videoDeleteDto){
        videoService.deleteOwnVideo(videoId, videoDeleteDto);
        return ResponseEntity.ok("Video was successfully deleted.");
    }

}
