package com.epam.hiber.dao;

import com.epam.hiber.entity.Item;
import com.epam.hiber.entity.Stock;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class Dao {

    @Autowired
    SessionFactory sessionFactory;

    public List<Stock> getStocks() {
        return (List<Stock>) sessionFactory.getCurrentSession()
                .createCriteria(Stock.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public List<Stock> getItems() {
        return (List<Stock>) sessionFactory.getCurrentSession()
                .createCriteria(Item.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void writeStock() {
        Item item = new Item();
        item.setName("MASERTA");
        item.setDate(new Date());

        Item item1 = new Item();
        item.setName("BERGAT");
        item.setDate(new Date());

        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item1);

        Stock stock = new Stock();
        stock.setName("SOVELENTO");
        stock.setCode("222%%%");
        stock.setItems(items);

        sessionFactory.getCurrentSession().persist(stock);
        sessionFactory.getCurrentSession().persist(item);
        sessionFactory.getCurrentSession().persist(item1);

        System.out.println("WRITED !!! ");
    }

}
