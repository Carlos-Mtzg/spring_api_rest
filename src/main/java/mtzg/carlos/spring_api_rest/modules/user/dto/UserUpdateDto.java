package mtzg.carlos.spring_api_rest.modules.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @Pattern(regexp = "^[^<>]*$", message = "No se permiten los caracteres < o >")
    private String name;

    @Pattern(regexp = "^[^<>]*$", message = "No se permiten los caracteres < o >")
    private String email;

    @Pattern(regexp = "^[^<>]*$", message = "No se permiten los caracteres < o >")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe contener exactamente 10 dígitos numéricos")
    private String phone;

    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.trim() : null;
    }

    public void setPhone(String phone) {
        this.phone = phone != null ? phone.trim() : null;
    }

}
