package com.guvi.expense_app.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseDto {
    private String item;
    private String description;
    private Long categoryId;
    private Double price;
    private LocalDate date;
}
