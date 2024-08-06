package com.example.simplepos.repository;

import com.example.simplepos.entity.Discount;
import com.example.simplepos.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    @Query(value = "SELECT * FROM discount WHERE discount_name = :paramValue", nativeQuery = true)
    Discount findDiscountByName(@Param("paramValue") String paramValue);

}
