package com.guvi.expense_app.service;

import com.guvi.expense_app.dto.CategoryDto;
import com.guvi.expense_app.model.Category;

public interface CategoryService {
    Category addCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);
}
