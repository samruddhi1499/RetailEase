package com.example.simplepos.service;

import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.entity.Inventory;
import com.example.simplepos.entity.InventoryPKId;
import com.example.simplepos.entity.Product;
import com.example.simplepos.entity.ProductCategory;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private InventoryService inventoryService;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long SKU) {
        return productRepository.findById(SKU).orElse(null);
    }

    public void addProduct(ProductDTO productDTO) {

        Product product = new Product();
        ProductCategory productCategory = productCategoryService.getProductCategoryById(productDTO.getProductCategoryID());

        product.setSKU(productDTO.getProductSKU());
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductCategory(productCategory);
        product.setProductCostPrice(productDTO.getProductCostPrice());
        product.setProductSellingPrice(productDTO.getProductSellingPrice());
        product.setIsExpirable(productDTO.getIsExpirable());
        product.setExpiryDate(productDTO.getExpiryDate());
        product.setStorageType(productDTO.getStorageType());

        productRepository.save(product);
    }



    public List<ProductDTO> getAllProducts() {

        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean updateProduct(ProductDTO productDTO) {

        Product product = productRepository.findById(productDTO.getProductSKU()).orElse(null);
        if(product != null){
            ProductCategory productCategory = productCategoryService.getProductCategoryById(productDTO.getProductCategoryID());

            product.setProductName(productDTO.getProductName() != null && !productDTO.getProductName().isEmpty() ? productDTO.getProductName() : product.getProductName());
            product.setProductDescription(productDTO.getProductDescription() != null && !productDTO.getProductDescription().isEmpty() ? productDTO.getProductDescription() : product.getProductDescription());
            product.setProductCategory(productDTO.getProductCategoryID() != null && productDTO.getProductCategoryID() > 0 ? productCategory : product.getProductCategory());
            product.setProductCostPrice(productDTO.getProductCostPrice() != null && productDTO.getProductCostPrice() > 0 ? productDTO.getProductCostPrice() : product.getProductCostPrice());
            product.setProductSellingPrice(productDTO.getProductSellingPrice() != null && productDTO.getProductSellingPrice() > 0 ? productDTO.getProductSellingPrice() : product.getProductSellingPrice());
            product.setIsExpirable(productDTO.getIsExpirable() instanceof Boolean  ? productDTO.getIsExpirable() : product.getIsExpirable());
            product.setExpiryDate(productDTO.getExpiryDate() instanceof Date ? productDTO.getExpiryDate() : product.getExpiryDate());
            product.setStorageType(productDTO.getStorageType() != null && !productDTO.getStorageType().isEmpty() ? productDTO.getStorageType() : product.getStorageType());

            productRepository.save(product);
            return true;
        }
        return false;

    }

    public void deleteProduct(ProductDTO productDTO) {
        inventoryService.deleteFromInventoryBySKU(productDTO.getProductSKU());
        Product byId = productRepository.findById(productDTO.getProductSKU()).orElse(null);
        if(byId != null) {
            productRepository.deleteById(productDTO.getProductSKU());
        }
    }
}
