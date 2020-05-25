package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resource {
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @Expose
    int year;
    @Expose
    String color;
    @SerializedName("pantone_value")
    @Expose
    String pantone_value;
}
