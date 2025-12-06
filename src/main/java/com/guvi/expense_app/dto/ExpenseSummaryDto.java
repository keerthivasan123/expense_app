package com.guvi.expense_app.dto;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseSummaryDto {
    private Double total;
    private Map<String, Double> distribution;
}
