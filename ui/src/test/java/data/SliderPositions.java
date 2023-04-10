package data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum SliderPositions {

    RECENT_AND_NEXT("Recent & Next", 0),
    TODAY("Today", 1),
    TOMORROW("Tomorrow", 2),
    THIS_WEEK("This Week", 3),
    NEXT_WEEK("Next Week", 4),
    THIS_MONTH("This Month", 5),
    NEXT_MONTH("Next Month", 6);

    private final String name;
    private final int position;

    private static final Map<String, SliderPositions> stringMap = Arrays.stream(values())
            .collect(Collectors.toMap(SliderPositions::getName, value -> value));

    public static SliderPositions getSliderPosition(String name) {
        return stringMap.get(name);
    }
}
