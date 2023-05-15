package com.example.springsecurityapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsecurityapplication.models.Warehouse;
import com.example.springsecurityapplication.repositories.WarehouseRepository;

@Service
@Transactional(readOnly = true)
public class WarehouseService {

	public final WarehouseRepository warehouseRepository;

	public WarehouseService(WarehouseRepository warehouseRepository) {
		super();
		this.warehouseRepository = warehouseRepository;
	}

	// Данный метод позволяет получить список категории
	public List<Warehouse> getAllwarehouse() {
		return warehouseRepository.findAll();
	}

	// Данный метод позволяет получить категорию по id
	public Warehouse getWarehouseById(int id) {
		Optional<Warehouse> optionalWrarehouse = warehouseRepository.findById(id);
		return optionalWrarehouse.orElse(null);
	}

	// Данный метод позволяет сохранить категорию
	@Transactional
	public void saveWarehouse(Warehouse warehouse) {
		warehouseRepository.save(warehouse);
	}

	// Данный метод позволяет обновить категорию
	@Transactional
	public void updateWarehouse(int id, Warehouse warehouse) {
		warehouse.setId(id);
		warehouseRepository.save(warehouse);
	}

	// Данный метод позволяет удалить категорию по id
	@Transactional
	public void deleteWarehouse(int id) {
		warehouseRepository.deleteById(id);
	}

}
