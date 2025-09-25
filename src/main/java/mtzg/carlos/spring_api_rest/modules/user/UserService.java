package mtzg.carlos.spring_api_rest.modules.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mtzg.carlos.spring_api_rest.modules.user.dto.UserRegisterDto;
import mtzg.carlos.spring_api_rest.modules.user.dto.UserResponseDto;
import mtzg.carlos.spring_api_rest.modules.user.dto.UserUpdateDto;
import mtzg.carlos.spring_api_rest.utils.Utilities;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<UserModel> users = userRepository.findAll();
            List<UserResponseDto> response = users.stream()
                    .map(user -> UserResponseDto.builder()
                            .uuid(user.getUuid().toString())
                            .name(user.getName())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .build())
                    .collect(Collectors.toList());
            return Utilities.dataResponse(HttpStatus.OK, "Consulta exitosa", response);
        } catch (Exception e) {
            return Utilities.simpleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getUserByUuid(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .map(user -> {
                    UserResponseDto response = UserResponseDto.builder()
                            .uuid(user.getUuid().toString())
                            .name(user.getName())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .build();
                    return Utilities.dataResponse(HttpStatus.OK, "Consulta exitosa", response);
                })
                .orElseGet(() -> Utilities.simpleResponse(HttpStatus.NOT_FOUND, "Registro no encontrado"));
    }

    @Transactional
    public ResponseEntity<Object> createUser(UserRegisterDto request) {
        try {
            Optional<UserModel> validMail = userRepository.findByEmail(request.getEmail());
            if (validMail.isPresent()) {
                return Utilities.simpleResponse(HttpStatus.CONFLICT,
                        "Ya existe un registro con este correo electrónico");
            }

            Optional<UserModel> validPhone = userRepository.findByPhone(request.getPhone());
            if (validPhone.isPresent()) {
                return Utilities.simpleResponse(HttpStatus.CONFLICT,
                        "Ya existe un registro con este número de teléfono");
            }

            UserModel user = UserModel.builder()
                    .uuid(UUID.randomUUID())
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .build();
            userRepository.save(user);

            return Utilities.simpleResponse(HttpStatus.CREATED, "Registro correcto");
        } catch (Exception e) {
            return Utilities.simpleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado");
        }
    }

    @Transactional
    public ResponseEntity<Object> updateUser(UUID uuid, UserUpdateDto dto) {
        try {
            return userRepository.findByUuid(uuid)
                    .map(existingUser -> {
                        if (dto.getName() != null) {
                            existingUser.setName(dto.getName());
                        }

                        if (dto.getEmail() != null && !dto.getName().equals(existingUser.getEmail())) {
                            return Utilities.simpleResponse(HttpStatus.CONFLICT,
                                    "Ya existe un registro con este correo electrónico");
                        }
                        existingUser.setEmail(dto.getEmail());

                        if (dto.getPhone() != null && !dto.getPhone().equals(existingUser.getPhone())) {
                            return Utilities.simpleResponse(HttpStatus.CONFLICT,
                                    "Ya existe un registro con este correo electrónico");
                        }
                        existingUser.setPhone(dto.getPhone());
                        userRepository.save(existingUser);
                        return Utilities.simpleResponse(HttpStatus.OK, "Actualización exitosa");
                    }).orElseGet(() -> Utilities.simpleResponse(HttpStatus.NOT_FOUND, "Registro no encontrado"));
        } catch (Exception e) {
            return Utilities.simpleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado");
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteUser(UUID uuid) {
        try {
            Optional<UserModel> userOpt = userRepository.findByUuid(uuid);
            if (!userOpt.isPresent()) {
                return Utilities.simpleResponse(HttpStatus.NOT_FOUND, "Registro no encontrado");
            }
            UserModel user = userOpt.get();
            userRepository.delete(user);
            return Utilities.simpleResponse(HttpStatus.OK, "El registro se eliminó correctamente");
        } catch (Exception e) {
            return Utilities.simpleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado");
        }
    }
}
