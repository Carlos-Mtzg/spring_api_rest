package mtzg.carlos.spring_api_rest.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utilities {
    private Utilities() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ResponseEntity<Object> apiResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("date", new Date());
            map.put("status", status.value());
            map.put("data", data);
            return new ResponseEntity<>(map, status);
        } catch (Exception e) {
            map.clear();
            map.put("date", new Date());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, status);
        }
    }
}
