package com.example.simplepos.repository;


import com.example.simplepos.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    @Query(value = "SELECT * FROM product_category WHERE category_name = :paramValue", nativeQuery = true)
    ProductCategory findByCategoryName(@Param("paramValue") String paramValue);
}
