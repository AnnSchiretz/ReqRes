package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUser {
    @Expose
    String name;
    @Expose
    String job;
    @Expose
    String updatedAt;

}
