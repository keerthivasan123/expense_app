package com.guvi.expense_app.service;

import com.guvi.expense_app.dto.ExpenseDto;
import com.guvi.expense_app.dto.ExpenseSummaryDto;
import com.guvi.expense_app.model.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    Expense addExpense(ExpenseDto expenseDto, Long userId);
    void deleteExpense(Long id);
    Expense updateExpense(Long id, ExpenseDto expenseDto);
    List<Expense> getExpensesByDate(LocalDate startDate, LocalDate endDate, Long userId);
    ExpenseSummaryDto getExpenseSummary(LocalDate startDate, LocalDate endDate, Long userId);
}
