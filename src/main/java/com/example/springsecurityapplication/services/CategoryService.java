package com.example.springsecurityapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class CategoryService {

	public final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	// Данный метод позволяет получить список категории
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	// Данный метод позволяет получить категорию по id
	public Category getCategoryById(int id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		return optionalCategory.orElse(null);
	}

	// Данный метод позволяет сохранить категорию
	@Transactional
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	// Данный метод позволяет обновить категорию
	@Transactional
	public void updateCategory(int id, Category category) {
		category.setId(id);
		categoryRepository.save(category);
	}

	// Данный метод позволяет удалить категорию по id
	@Transactional
	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
	}

}
