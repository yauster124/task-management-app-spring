package com.dorsetsoftware.store.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dorsetsoftware.store.user.User;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByUser(User user);
}
