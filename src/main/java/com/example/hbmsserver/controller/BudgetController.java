package com.example.hbmsserver.controller;

import com.example.hbmsserver.dao.BudgetDao;
import com.example.hbmsserver.dto.BudgetInfoDto;
import com.example.hbmsserver.dto.BudgetSpentValueDto;
import com.example.hbmsserver.dto.PostBudgetDetails;
import com.example.hbmsserver.model.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/budgets")
@CrossOrigin(origins = "http://localhost:3000")
public class BudgetController {

    @Autowired
    private BudgetDao budgetDao;

    @GetMapping("getAll/{date}")
    public List<BudgetInfoDto> getAll(@RequestParam("date") String date) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = Long.parseLong(authentication.getName());

        var budgets = budgetDao.findAll(userId, date);
        var a = budgetDao.getBudgetsSpentValue(userId, date);
        var budgetsSpentValues = a.stream()
                .map(el -> new BudgetSpentValueDto(Long.parseLong(el[0].toString()), Double.parseDouble(el[1].toString())))
                .collect(Collectors.toList());

        return StreamSupport.stream(budgets.spliterator(), false)
                .map(budget -> {
                    var budgetsSpentValue = budgetsSpentValues.stream()
                            .filter(bsv -> budget.getCategoryType().getId().equals(bsv.getCategoryTypeId()))
                            .findFirst();

                    return budgetsSpentValue.map(spentValue -> new BudgetInfoDto(
                            budget,
                            spentValue.getSpentValue()))
                            .orElseGet(() -> new BudgetInfoDto(budget, 0D));

                })
                .collect(Collectors.toList());
    }

    @PostMapping("add")
    public BudgetInfoDto add(@RequestBody PostBudgetDetails budgetDetails) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = Long.parseLong(authentication.getName());

        var newBudgetId = budgetDao.save(new Budget(budgetDetails, userId)).getId();
        var newBudget = budgetDao.getOne(newBudgetId);
        return new BudgetInfoDto(newBudget,0D);
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@RequestParam("id") Long budgetId) {
        budgetDao.deleteById(budgetId);
        return true;
    }
}
