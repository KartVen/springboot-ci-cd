package pl.kartven.springboot_ci_cd;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RestApiController {
    @GetMapping("/get")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/post")
    public ResponseEntity<String> post() {
        return ResponseEntity.created(URI.create("/get")).build();
    }
}
