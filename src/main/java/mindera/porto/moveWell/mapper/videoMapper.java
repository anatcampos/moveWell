package mindera.porto.moveWell.mapper;

import mindera.porto.moveWell.dto.videoCreateDto;
import mindera.porto.moveWell.dto.VideoReadDto;
import mindera.porto.moveWell.entity.Video;

public class videoMapper {

    public videoMapper() {
    }

    public static VideoReadDto fromVideoToVideoReadDto (Video video) {
        VideoReadDto videoReadDto = new VideoReadDto();
        videoReadDto.setId(video.getId());
        videoReadDto.setUrl(video.getUrl());
        videoReadDto.setNumberOfRatings(video.getNumberOfRatings());
        videoReadDto.setAverageRating(video.getAverageRating());
        videoReadDto.setNumberOfViews(video.getNumberOfViews());
        return videoReadDto;
    }
    public static Video fromVideoCreateDtoToVideo (videoCreateDto videoCreateDto){
        Video video;
        video = new Video();
        video.setUrl (videoCreateDto.getUrl ());
        video.setAverageRating(videoCreateDto.getAverageRating());











    }
}
