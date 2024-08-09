package com.example.simplepos.service;


import com.example.simplepos.dto.DiscountDTO;
import com.example.simplepos.entity.Discount;
import com.example.simplepos.entity.Product;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.DiscountRepository;
import com.example.simplepos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    private final ProductRepository productRepository;


    public DiscountService(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
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

    public boolean updateDiscount(DiscountDTO discountDTO) {

        Discount discountCheck = discountRepository.findById(discountDTO.getDiscountId()).orElse(null);

        if(discountCheck != null){


            discountCheck.setDiscountName(!discountDTO.getDiscountName().isEmpty() && discountDTO.getDiscountName() != null ? discountDTO.getDiscountName():discountCheck.getDiscountName());
            discountCheck.setDiscountPercent(discountDTO.getDiscountPercent() > 0 && discountDTO.getDiscountPercent() != null ? discountDTO.getDiscountPercent(): discountCheck.getDiscountPercent());
            discountCheck.setDiscountExpiryDate(discountDTO.getDiscountExpiryDate() != null ? discountDTO.getDiscountExpiryDate(): discountCheck.getDiscountExpiryDate());
            Date todayDate = new Date();
            discountCheck.setIsActive(discountCheck.getDiscountExpiryDate().compareTo(todayDate) != 0 && discountCheck.getDiscountExpiryDate().compareTo(todayDate) >= 0);
            discountRepository.save(discountCheck);

            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteDiscount(Integer id) {

        List<Product> byDiscountId = productRepository.findByDiscountId(id);

        if( byDiscountId.isEmpty() ){
            discountRepository.deleteById(id);
            return true;
        }
        else{
            Discount byId = discountRepository.findById(id).orElse(null);
            if(byId != null && Boolean.FALSE.equals(byId.getIsActive())){
                productRepository.deleteDiscountId(null,id);
                discountRepository.deleteById(id);
                return true;
                }
            return false;
        }

    }
}
