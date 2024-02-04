package com.curd.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class delete {

    @DeleteMapping("/documents/{documentId}")
    public String deleteDocument(@PathVariable String documentId) {
        {
            try {
                Firestore dbFirestore = FirestoreClient.getFirestore();
                ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("CRM").document(documentId).delete();
                return "Successfully deleted " + documentId;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
