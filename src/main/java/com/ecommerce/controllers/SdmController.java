package com.ecommerce.controllers;

import com.ecommerce.models.DataModel;
import com.ecommerce.repository.SdmRepository;
import com.ecommerce.services.SdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SdmController {

    @Autowired
    private SdmService sdmService;

    @GetMapping("/data-models")
    public String getDataModels() {
        return "Data models";
    }

    @PostMapping("/SystemDataModel")
    public String createSDM(@RequestBody DataModel datamodel) {
        sdmService.createRecipe(datamodel);
        return "Created System Data Model";
    }
}
