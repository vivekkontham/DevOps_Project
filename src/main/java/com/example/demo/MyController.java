package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class MyController {

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        return Arrays.asList(
            Map.of("id", 1, "name", "Alice"),
            Map.of("id", 2, "name", "Bob")
        );
    }

    @GetMapping("/data")
    public Map<String, String> getData() {
        return Map.of(
            "key1", "value1",
            "key2", "value2"
        );
    }

    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "running");
    }
}
