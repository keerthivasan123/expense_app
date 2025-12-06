package com.guvi.expense_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {
    @NotBlank(message = "Category name cannot be blank")
    private String categoryName;
}
