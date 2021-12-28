package com.gec.service;

import com.gec.dao.CategoryDao;
import com.gec.entity.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao=new CategoryDao();
    public List<Category> getCategoryList(){

        return categoryDao.getCategoryList();

    }
}
