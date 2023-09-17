package ru.tusur.bookreaderservice.util;

import ru.tusur.bookreaderservice.dto.EventRating;

public class RatingCalculatorUtil {

    public static long calculateRating(EventRating eventRating) {
        long rating = eventRating.getLikeCounter() - eventRating.getDislikeCounter();
        return rating < 0 ? 0 : rating;
    }
}
