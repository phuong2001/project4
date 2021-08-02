package com.project.lpd.service;


import com.project.lpd.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {
    @Autowired
    CartRepo cartRepo;

}
