package com.curd.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("CRM").document(crud.getDocumentId());
            ApiFuture<WriteResult> collectionsApiFuture = documentReference.set(crud);
            WriteResult writeResult = collectionsApiFuture.get();

            // Format the update time as a String
            Date updateTime = writeResult.getUpdateTime().toDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(updateTime);
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return "Error creating CRUD";
        }
    }

    /**
     * Retrieves a CRUD object by document ID.
     *
     * @param documentId The document ID to retrieve.
     * @return The CRUD object or null if not found.
     */
    public CRUD getCRUD(String documentId) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("CRM").document(documentId);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()){
                return document.toObject(CRUD.class);
            }
            return null;
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates a CRUD object.
     *
     * @param crud The CRUD object to update.
     * @return The updated document's update time as a formatted string.
     */
    public String updateCRUD(CRUD crud) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("CRM").document(crud.getDocumentId()).set(crud);
            return collectionsApiFuture.get().getUpdateTime().toString();
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return "Error updating CRUD";
        }
    }

    /**
     * Deletes a CRUD object by document ID.
     *
     * @param documentId The document ID to delete.
     * @return A success message after deletion.
     */
    public String deleteCRUD(String documentId) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("CRM").document(documentId).delete();
            return "Successfully deleted " + documentId;
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return "Error deleting CRUD";
        }
    }
}
