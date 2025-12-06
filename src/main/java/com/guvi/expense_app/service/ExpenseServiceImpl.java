package com.guvi.expense_app.service;

import com.guvi.expense_app.dto.ExpenseDto;
import com.guvi.expense_app.dto.ExpenseSummaryDto;
import com.guvi.expense_app.model.Category;
import com.guvi.expense_app.model.Expense;
import com.guvi.expense_app.model.User;
import com.guvi.expense_app.repository.CategoryRepository;
import com.guvi.expense_app.repository.ExpenseRepository;
import com.guvi.expense_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Expense addExpense(ExpenseDto expenseDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findById(expenseDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        Expense expense = new Expense();
        expense.setItem(expenseDto.getItem());
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(category);
        expense.setPrice(expenseDto.getPrice());
        expense.setDate(expenseDto.getDate());
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense updateExpense(Long id, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        Category category = categoryRepository.findById(expenseDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        expense.setItem(expenseDto.getItem());
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(category);
        expense.setPrice(expenseDto.getPrice());
        expense.setDate(expenseDto.getDate());
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getExpensesByDate(LocalDate startDate, LocalDate endDate, Long userId) {
        return expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }

    @Override
    public ExpenseSummaryDto getExpenseSummary(LocalDate startDate, LocalDate endDate, Long userId) {
        List<Expense> expenses = expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
        Double total = expenses.stream().mapToDouble(Expense::getPrice).sum();
        Map<String, Double> distribution = new HashMap<>();
        for (Expense expense : expenses) {
            String categoryName = expense.getCategory().getCategoryName();
            distribution.put(categoryName, distribution.getOrDefault(categoryName, 0.0) + expense.getPrice());
        }
        for (Map.Entry<String, Double> entry : distribution.entrySet()) {
            entry.setValue((entry.getValue() / total) * 100);
        }
        return new ExpenseSummaryDto(total, distribution);
    }
}
