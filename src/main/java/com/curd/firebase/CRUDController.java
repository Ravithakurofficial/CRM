package com.curd.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CRUDController {

    private final CRUDService crudService;

    @Autowired
    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        try {
            List<CRUD> allData = crudService.getAllCRUD();
            model.addAttribute("data", allData);
            return "dashboard";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while fetching data");
            return "error";
        }
    }
}
