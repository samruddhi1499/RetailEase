package com.example.simplepos.service;

import com.example.simplepos.dto.WarehouseDTO;
import com.example.simplepos.entity.Warehouse;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {


    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse getWarehouseById(Integer warehouseId){


        return warehouseRepository.findById(warehouseId).orElse(null);

    }

    public void addWarehouse(WarehouseDTO warehouseDTO) {

        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouseID(warehouseDTO.getWarehouseID());
        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());

    }

    public List<WarehouseDTO> getAllWarehouse() {

        List<Warehouse> allWarehouse = warehouseRepository.findAll();
        return allWarehouse.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());

    }

    public void updateWarehouse(WarehouseDTO warehouseDTO) {

        Warehouse warehouse = warehouseRepository.findById(warehouseDTO.getWarehouseID()).orElse(null);
        if(warehouse != null){
            warehouse.setWarehouseName(warehouseDTO.getWarehouseName() != null && !warehouseDTO.getWarehouseName().isEmpty() ? warehouseDTO.getWarehouseName() : warehouse.getWarehouseName());
        }
    }

}
