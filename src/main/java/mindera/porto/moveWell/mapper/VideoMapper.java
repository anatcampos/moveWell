package mindera.porto.moveWell.mapper;

import mindera.porto.moveWell.dto.VideoCreateDto;
import mindera.porto.moveWell.dto.VideoReadDto;
import mindera.porto.moveWell.entity.Video;

public class VideoMapper {

    public static VideoReadDto fromVideoToVideoReadDto (Video video) {
        VideoReadDto videoReadDto = new VideoReadDto();
        videoReadDto.setId(video.getId());
        videoReadDto.setUrl(video.getUrl());
        videoReadDto.setNumberOfRatings(video.getNumberOfRatings());
        videoReadDto.setAverageRating(video.getAverageRating());
        videoReadDto.setNumberOfViews(video.getNumberOfViews());
        return videoReadDto;
    }
    public static Video fromVideoCreateDtoToVideo (VideoCreateDto videoCreateDto) {
        Video video = new Video();
        video.setUrl(videoCreateDto.getUrl());
        return video;
    }


}
