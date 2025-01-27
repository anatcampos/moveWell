package mindera.porto.moveWell.controller;

import mindera.porto.moveWell.dto.UserCreateDto;
import mindera.porto.moveWell.dto.UserReadDto;
import mindera.porto.moveWell.entity.Video;
import mindera.porto.moveWell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<UserReadDto> getUser(@PathVariable("userId") Long userId){
        try {
            UserReadDto user = this.userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public UserReadDto addNewUser(@RequestBody UserCreateDto user){
        return userService.addNewUser(user);
    }

    @GetMapping("{userId}/video-views")
    public UserVideosDto getVideoViews(@PathVariable("userId") Long userId){

    }

    @GetMapping("{userId}/video-views-other-way")
    public  List<VideosWithViewDto> getVideoViewsOtherWay(@PathVariable("userId") Long userId){

    }

    //User ver a sua subscrição

    //User ver que vídeos já visualizei e quantos visualizou

    //

    public static class VideoDto{
       Integer id;
       String url;
    }


    public static class VideosWithViewDto{
        VideoDto videoDto;
        Integer viewCount;
    }

    public static class UserVideosDto{
        List<VideosWithViewDto> videosWithViewDtoList;
    }

    /*
    {
       "videosWithViewDtoList": [
                                {
                                 "videoDto":{"id":1, "url":"https://...."},
                                 "viewCount":10
                                 },
                                  {
                                 "videoDto":{"id":2, "url":"https://...."},
                                 "viewCount":1
                                 }


                             ]
    }


   [
        {
         "videoDto":{"id":1, "url":"https://...."},
         "viewCount":10
         },
        {
         "videoDto":{"id":2, "url":"https://...."},
         "viewCount":1
        }
 ]
    *
    * */

}
