package mindera.porto.moveWell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (path = "api/v1/videos")
public class VideoController {
    private final mindera.porto.moveWell.service.videoService videoService;
@Autowired
    public VideoController(mindera.porto.moveWell.service.videoService videoService) {
        this.videoService = videoService;

    }

//    @GetMapping (path = "{videoId}")
    public ResponseEntity <VideoReadDto> getVideo (@PathVariable("videoId") Long videoId){
    try {
        VideoReadDto video = this.videoService.getVideoById(videoId);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }catch (IllegalStateException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
//    @DeleteMapping (path = "{videoId}" )
//    public ResponseEntity<String> deleteVideo (@PathVariable ("videoId") Long videoId, @RequestBody VideoDeleteDto videoDeleteDto){
//        videoService.
        }
}}

