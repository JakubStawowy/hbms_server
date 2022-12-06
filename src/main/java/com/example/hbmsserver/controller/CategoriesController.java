package com.example.hbmsserver.controller;

import com.example.hbmsserver.dao.CategoryDao;
import com.example.hbmsserver.dao.CategoryTypesDao;
import com.example.hbmsserver.dao.TransactionDao;
import com.example.hbmsserver.dao.UserDao;
import com.example.hbmsserver.dto.CategoryInfoDto;
import com.example.hbmsserver.dto.CategoryTypesDto;
import com.example.hbmsserver.dto.PostCategoryDetails;
import com.example.hbmsserver.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriesController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private CategoryTypesDao categoryTypesDto;

    @Autowired
    private UserDao userDao;

    @GetMapping("getAll/{date}")
    public List<CategoryInfoDto> getAll(@RequestParam("date") String date) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = Long.parseLong(authentication.getName());

        var categories = categoryDao.findAll(userId, date);
        var categoriesDetails = transactionDao.getCategoriesDetails();

        return StreamSupport.stream(categories.spliterator(), false).map(category -> {
                    var catDetails = categoriesDetails.stream()
                            .filter(catD -> catD.getId() == category.getId())
                            .findFirst();

                    return catDetails.map(categoryDetails -> new CategoryInfoDto(
                            category,
                            categoryDetails.getRecordsNumber(),
                            categoryDetails.getTransactionsValue()))
                            .orElseGet(() -> new CategoryInfoDto(category, 0L, 0D));
                }
        ).collect(Collectors.toList());
    }

    @GetMapping("getAllTypes")
    public List<CategoryTypesDto> getAllTypes() {
        var categoryTypes = categoryTypesDto.findAll();

        return StreamSupport.stream(categoryTypes.spliterator(), false).map(type -> new CategoryTypesDto(
                type.getId(),
                type.getName(),
                type.getIcon(),
                type.getColor()
        )).collect(Collectors.toList());
    }

    @PostMapping("add")
    public CategoryInfoDto add(@RequestBody PostCategoryDetails categoryDetails) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = Long.parseLong(authentication.getName());
        var optionalUser = userDao.findUserById(userId);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with this emailAddress does not exists");
        }

        var category = new Category(categoryDetails, optionalUser.get());
        category = categoryDao.save(category);
        return new CategoryInfoDto(
                category,
                0L,
                0D
        );
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@RequestParam("id") Long categoryId) {
        categoryDao.deleteById(categoryId);
        return true;
    }
}
