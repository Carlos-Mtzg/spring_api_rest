package mtzg.carlos.spring_api_rest.modules.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mtzg.carlos.spring_api_rest.modules.user.dto.UserRequestDto;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getUserByUuid(@PathVariable("uuid") UUID uuid) {
        return userService.getUserByUuid(uuid);
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> updateUser(@PathVariable("uuid") UUID uuid, @RequestBody UserRequestDto request) {
        return userService.updateUser(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> deleteUser(@PathVariable("uuid") UUID uuid) {
        return userService.deleteUser(uuid);
    }
}
