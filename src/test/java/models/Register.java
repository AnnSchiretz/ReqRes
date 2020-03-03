package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Register {
    @Expose
    String email;
    @Expose
    String password;

    public Register(String email) {
        this.email = email;
    }
}
