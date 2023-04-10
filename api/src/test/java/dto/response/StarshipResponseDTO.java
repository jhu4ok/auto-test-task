package dto.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class StarshipResponseDTO {

    @SerializedName("max_atmosphering_speed")
    private String maxAtmospheringSpeed;
    @SerializedName("cargo_capacity")
    private String cargoCapacity;
    private List<String> films;
    private String passengers;
    private List<String> pilots;
    private String edited;
    private String consumables;
    @SerializedName("MGLT")
    private String mGLT;
    private String created;
    private String length;
    @SerializedName("starship_class")
    private String starshipClass;
    private String url;
    private String manufacturer;
    private String crew;
    @SerializedName("hyperdrive_rating")
    private String hyperdriveRating;
    @SerializedName("cost_in_credits")
    private String costInCredits;
    private String name;
    private String model;

}