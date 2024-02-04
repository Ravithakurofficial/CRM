package com.curd.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final CRUDService crudService;

    @Autowired
    public RestController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.createCRUD(crud);
    }

    @GetMapping("/get")
    public CRUD getCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return crudService.getCRUD(documentId);
    }

    @PutMapping("/update")
    public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.updateCRUD(crud);
    }

    @DeleteMapping("/delete")
    public String deleteCRUD(@PathVariable String documentId) throws InterruptedException, ExecutionException {
        return crudService.deleteCRUD(documentId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test Get Endpoint is working");
    }
}
