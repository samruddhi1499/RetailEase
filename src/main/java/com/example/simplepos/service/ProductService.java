package com.example.simplepos.service;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.entity.Product;
import com.example.simplepos.entity.ProductCategory;
import com.example.simplepos.repository.ProductRepository;
import com.example.simplepos.repository.WarehouseRepository;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class ProductService {


    private final ProductRepository productRepository;


    private final ProductCategoryService productCategoryService;


    private final WarehouseRepository warehouseRepository;

    private final InventoryService inventoryService;

    public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService, WarehouseRepository warehouseRepository, InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
        this.warehouseRepository = warehouseRepository;
        this.inventoryService = inventoryService;
    }

    public Product getProductById(Long sku) {
        return productRepository.findById(sku).orElse(null);
    }

    public boolean addProduct(ProductDTO productDTO, Date expiryDate) {

        Product productCheck = productRepository.findById(productDTO.getProductSKU()).orElse(null);
        if(productCheck == null){

            Product product = new Product();
            Integer warehouseId = warehouseRepository.findByWarehouseName(productDTO.getWarehouseName());

            ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDTO.getProductCategoryName());

            product.setSKU(productDTO.getProductSKU());
            product.setProductName(productDTO.getProductName());
            product.setProductDescription(productDTO.getProductDescription());
            product.setProductCategory(productCategory);
            product.setProductCostPrice(productDTO.getProductCostPrice());
            product.setProductSellingPrice(productDTO.getProductSellingPrice());
            product.setIsExpirable(productDTO.getIsExpirable());
            product.setExpiryDate(expiryDate);
            product.setStorageType(productDTO.getStorageType());
            if (productDTO.getProductImage().isEmpty()) {
                product.setProductImage(null);
            } else {
                product.setProductImage(productDTO.getProductImage());
            }
            productRepository.save(product);

            inventoryService.addToInventory(new InventoryDTO(productDTO.getProductSKU(),warehouseId,productDTO.getProductQuantity(),null,null));

            return true;
        }
        return false;
    }



//    public List<ProductDTO> getAllProducts() {
//
//        List<Product> allProducts = productRepository.findAll();
//        return allProducts.stream()
//                .map(DTOMapper::toDTO)
//                .collect(Collectors.toList());
//
//    }

    public boolean updateProduct(ProductDTO productDTO, Date expirayDate) {

        Product product = productRepository.findById(productDTO.getProductSKU()).orElse(null);
        Integer warehouseId = warehouseRepository.findByWarehouseName(productDTO.getWarehouseName());

        if(product != null){
            ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDTO.getProductCategoryName());

            product.setProductName(productDTO.getProductName() != null && !productDTO.getProductName().isEmpty() ? productDTO.getProductName() : product.getProductName());
            product.setProductDescription(productDTO.getProductDescription() != null && !productDTO.getProductDescription().isEmpty() ? productDTO.getProductDescription() : product.getProductDescription());
            product.setProductCategory(productDTO.getProductCategoryName() != null && productDTO.getProductCategoryName().isEmpty() ? productCategory : product.getProductCategory());
            product.setProductCostPrice(productDTO.getProductCostPrice() != null && productDTO.getProductCostPrice() > 0 ? productDTO.getProductCostPrice() : product.getProductCostPrice());
            product.setProductSellingPrice(productDTO.getProductSellingPrice() != null && productDTO.getProductSellingPrice() > 0 ? productDTO.getProductSellingPrice() : product.getProductSellingPrice());
            product.setIsExpirable(productDTO.getIsExpirable() instanceof Boolean  ? productDTO.getIsExpirable() : product.getIsExpirable());
            product.setExpiryDate(expirayDate != null ? expirayDate : product.getExpiryDate());
            product.setStorageType(productDTO.getStorageType() != null && !productDTO.getStorageType().isEmpty() ? productDTO.getStorageType() : product.getStorageType());
            product.setProductImage(productDTO.getProductImage() != null ? productDTO.getProductImage(): product.getProductImage());
            productRepository.save(product);

            inventoryService.updateToInventory(new InventoryDTO(productDTO.getProductSKU(),warehouseId,productDTO.getProductQuantity(),null,null));

            return true;
        }
        return false;

    }

    public boolean deleteProduct(ProductDTO productDTO) {
        List<InventoryDTO> entryById = inventoryService.getEntryById(productDTO.getProductSKU());
        if( entryById.isEmpty() ){
            productRepository.deleteById(productDTO.getProductSKU());
            return true;
        }
        return false;

    }
}
