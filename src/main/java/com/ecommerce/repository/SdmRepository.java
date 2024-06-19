package com.ecommerce.repository;

import com.ecommerce.models.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SdmRepository extends JpaRepository<DataModel,Long> {
}
