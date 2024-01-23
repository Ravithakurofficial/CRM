package com.curd.firebase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class Controller { // Renamed to follow Java naming conventions
    private CRUDService crudService; // Renamed to follow Java naming conventions

    public Controller(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.createCRUD(crud);
    }

    @GetMapping("/get") // Fixed the annotation typo
    public String getCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return crudService.getCRUD(documentId);
    }

    @PutMapping("/update")
    public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.updateCRUD(crud);
    }

    @DeleteMapping("/delete") // Changed to DeleteMapping
    public String deleteCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return crudService.deleteCRUD(documentId);
    }
    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test Get Endpoint is working");
    }
}
