package dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class PeopleResponseDTO {

    private List<String> films;
    private String homeworld;
    private String gender;
    @SerializedName("skin_color")
    private String skinColor;
    @SerializedName("hair_color")
    private String hairColor;
    @SerializedName("eye_color")
    private String eyeColor;
    @SerializedName("birth_year")
    private String birthYear;
    private String edited;
    private String created;
    private String mass;
    private List<Object> vehicles;
    private String url;
    private List<Object> species;
    private List<String> starships;
    private String name;
    private String height;

}