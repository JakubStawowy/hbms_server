package com.example.hbmsserver.controller;

import com.example.hbmsserver.dao.CategoryDao;
import com.example.hbmsserver.dao.TransactionDao;
import com.example.hbmsserver.dto.TransactionInfoDto;
import com.example.hbmsserver.dto.TransactionsCategoryDto;
import com.example.hbmsserver.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
@CrossOrigin("*")
public class TransactionsController {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("getAll/{id}")
    public TransactionsCategoryDto getAll(@RequestParam("id") Long categoryId) {
        var transactions = transactionDao.findAll(categoryId);
        var category = categoryDao.getOne(categoryId);

        return new TransactionsCategoryDto(
                category.getCategoryType().getName(),
                category.getDate(),
                category.getCategoryType().getColor(),
                category.getCategoryType().getIcon(),
                transactions.stream()
                        .map(transaction -> new TransactionInfoDto(
                                transaction.getId(),
                                transaction.getName(),
                                transaction.getValue(),
                                transaction.getDate()
                        ))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("add/{id}")
    public TransactionInfoDto add(@RequestParam("id") Long categoryId, @RequestBody TransactionInfoDto transactionInfoDto) {
        var optionalCategory = categoryDao.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new IllegalArgumentException("User with this emailAddress does not exists");
        }
        var transaction = new Transaction(transactionInfoDto, optionalCategory.get());
        transactionDao.save(transaction);
        return transactionInfoDto;
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@RequestParam("id") Long transactionId) {
        transactionDao.deleteById(transactionId);
        return true;
    }
}
