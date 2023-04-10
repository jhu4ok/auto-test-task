package dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class FilmResponseDTO {

    private int count;
    private Object next;
    private Object previous;
    private List<ResultsItem> results;

    @Data
    public static class ResultsItem {

        private String title;
        @SerializedName("episode_id")
        private int episodeId;
        private String edited;
        private String director;
        private String created;
        private List<String> vehicles;
        private String openingCrawl;
        private String url;
        private List<String> characters;
        private List<String> planets;
        private String releaseDate;
        private List<String> starships;
        private List<String> species;
        private String producer;

    }

}
