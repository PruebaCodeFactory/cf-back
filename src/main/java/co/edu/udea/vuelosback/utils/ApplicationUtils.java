package co.edu.udea.vuelosback.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ApplicationUtils {

    private ApplicationUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus) {
        return new ResponseEntity<String>("Mensaje : " + message, httpStatus);
    }

    public static ResponseEntity<Map<String, String>> generateResponse(
            Map<String, String> response,
            HttpStatus httpStatus
    ) {
        return new ResponseEntity<Map<String, String>>(response, httpStatus);
    }


}
