package com.curd.firebase;// CRUDService.java
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("CRM").document(crud.getDocumentId());
            ApiFuture<WriteResult> collectionsApiFuture = documentReference.set(crud);
            WriteResult writeResult = collectionsApiFuture.get();

            return writeResult.getUpdateTime().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating CRUD";
        }
    }

    public CRUD getCRUD(String documentId) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("CRM").document(documentId);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            return document.exists() ? document.toObject(CRUD.class) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateCRUD(CRUD crud) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("CRM").document(crud.getDocumentId()).set(crud);
            return collectionsApiFuture.get().getUpdateTime().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating CRUD";
        }
    }

    public String deleteCRUD(String documentId) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("CRM").document(documentId).delete();
            return "Successfully deleted " + documentId;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting CRUD";
        }
    }
    public List<CRUD> getAllCRUD() {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            CollectionReference collectionReference = dbFirestore.collection("CRM");
            ApiFuture<QuerySnapshot> future = collectionReference.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<CRUD> allCRUD = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                allCRUD.add(document.toObject(CRUD.class));
            }

            return allCRUD;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}