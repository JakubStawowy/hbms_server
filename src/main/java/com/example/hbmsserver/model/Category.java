package com.example.hbmsserver.model;

import com.example.hbmsserver.dto.CategoryInfoDto;
import com.example.hbmsserver.dto.PostCategoryDetails;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_type_id", referencedColumnName = "id")
    private CategoryType categoryType;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @Column(name = "date")
    private String date;

    public Category() {
    }

    public Category(User user, CategoryType categoryType, String date) {
        this.user = user;
        this.categoryType = categoryType;
        this.date = date;
    }

    public Category(PostCategoryDetails categoryDetails, User user) {
        this.user = user;
        categoryType = new CategoryType(
                categoryDetails.getId(),
                categoryDetails.getName(),
                categoryDetails.getIcon(),
                categoryDetails.getColor()
        );
        date = categoryDetails.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}