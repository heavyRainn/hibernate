package com.epam.hiber.main;

import com.epam.hiber.config.SpringConfig;
import com.epam.hiber.dao.Dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        Dao dao = ctx.getBean(Dao.class);

       // dao.writeStock();

        System.out.println(dao.getStocks());
        System.out.println(dao.getItems());
    }

}
