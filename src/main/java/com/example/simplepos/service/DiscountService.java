package com.example.simplepos.service;


import com.example.simplepos.dto.DiscountDTO;
import com.example.simplepos.entity.Discount;
import com.example.simplepos.entity.ProductCategory;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;


    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Discount getDiscountByName(String discountName){
        return discountRepository.findDiscountByName(discountName);
    }

    public List<DiscountDTO> getAllDiscount() {

        List<Discount> allDiscount = discountRepository.findAll();
        return allDiscount.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean addDiscount(DiscountDTO discountDTO) {

        Discount discountCheck = discountRepository.findDiscountByName(discountDTO.getDiscountName());

        if(discountCheck == null){

            Discount discount = new Discount();
            discount.setDiscountName(discountDTO.getDiscountName());
            discount.setDiscountPercent(discountDTO.getDiscountPercent());
            discount.setDiscountExpiryDate(discountDTO.getDiscountExpiryDate());
            Date todayDate = new Date();
            discount.setIsActive(discount.getDiscountExpiryDate().compareTo(todayDate) != 0 && discount.getDiscountExpiryDate().compareTo(todayDate) >= 0);
            discountRepository.save(discount);

            return true;
        }
        return false;
    }

    public void checkIfActive() {
        Date todayDate = new Date();
        for (Discount discount : discountRepository.findAll()) {

            discount.setIsActive(discount.getDiscountExpiryDate().compareTo(todayDate) != 0 && discount.getDiscountExpiryDate().compareTo(todayDate) >= 0);
            discountRepository.save(discount);
        }


    }
}
