package com.curd.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class update{

    @PutMapping("/update/{documentId}")
    public String updateCRUD(@PathVariable String documentId, @RequestBody CRUD crud) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> writeResult = dbFirestore.collection("CRM").document(documentId).set(crud);
            return writeResult.get().getUpdateTime().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating CRUD";
        }
    }

}
