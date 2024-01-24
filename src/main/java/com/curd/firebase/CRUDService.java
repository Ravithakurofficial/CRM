package com.curd.firebase;

import org.springframework.stereotype.Service;

@Service
public class CRUDService {
    public String createCRUD(CRUD crud){
        return "hi this is test";
    }
    public String getCRUD(String documentId) {

        return "hi";
    }
    public String updateCRUD(CRUD crud) {
        return "hii";
    }
    public String deleteCRUD(String documentId) {
        return "hii";
    }

}
