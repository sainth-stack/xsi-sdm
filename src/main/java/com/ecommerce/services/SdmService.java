package com.ecommerce.services;

import com.ecommerce.models.DataModel;
import com.ecommerce.models.Recipe;
import com.ecommerce.models.User;
import com.ecommerce.repository.SdmRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SdmService {
    @Autowired
    private SdmRepository sdmRepository;

    public DataModel createRecipe(DataModel data) {
        return sdmRepository.save(data);
    }
}
