package models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class RegisterInfo {
    @Expose
    String id;
    @Expose
    String token;
}
