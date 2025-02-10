package mindera.porto.moveWell.mapper;

import mindera.porto.moveWell.dto.RatingCreateDto;
import mindera.porto.moveWell.dto.RatingReadDto;
import mindera.porto.moveWell.entity.Rating;

public class RatingMapper {

    public static RatingReadDto fromRatingToRatingReadDto(Rating rating) {
        RatingReadDto ratingReadDto = new RatingReadDto();
        ratingReadDto.setRatingStar(rating.getRatingStar());
        return ratingReadDto;
    }

    public static Rating fromRatingCreateDtoToRating(RatingCreateDto ratingCreateDto) {
        Rating rating = new Rating();
        rating.setRatingStar(ratingCreateDto.getRatingStar());
        return rating;
    }
}
