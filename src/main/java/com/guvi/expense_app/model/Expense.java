package com.guvi.expense_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Double price;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
