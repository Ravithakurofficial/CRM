package com.curd.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;

@RestController
public class delete {

    @DeleteMapping("/delete/{documentId}")
    public String deleteDocument(@PathVariable String documentId) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> deleteFuture = dbFirestore.collection("CRM").document(documentId).delete();

            // Wait for the deletion operation to complete
            deleteFuture.get();

            return "Document with ID: " + documentId + " deleted successfully."; // Return success message
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error deleting document: " + e.getMessage(); // Return error message
        }
    }
}
