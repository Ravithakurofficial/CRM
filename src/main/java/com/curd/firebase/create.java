package com.curd.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class create {

    @PostMapping("/api/data")
    public ResponseEntity<String> updateCRUD(@RequestBody CRUD crud) {
        try {
            String documentId = crud.getDocumentId();
            if (documentId == null || documentId.isEmpty()) {
                return ResponseEntity.badRequest().body("DocumentId cannot be empty");
            }

            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("CRM").document(documentId).set(crud);
            return ResponseEntity.ok().body(collectionsApiFuture.get().getUpdateTime().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating CRUD");
        }
    }
}
