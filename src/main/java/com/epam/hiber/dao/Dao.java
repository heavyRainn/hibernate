package com.epam.hiber.dao;

import com.epam.hiber.entity.Item;
import com.epam.hiber.entity.Stock;
import org.hibernate.Session;
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

    public static final String EXTRACT_STOCKS = "from Stock s ";
    public static final String EXTRACT_ITEMS = "from Item i ";

    public List<Stock> getStocks() {
        return (List<Stock>) sessionFactory.getCurrentSession()
                .createQuery(EXTRACT_STOCKS)
                .list();
    }

    public List<Item> getItems() {
        return (List<Item>) sessionFactory.getCurrentSession()
                .createQuery(EXTRACT_ITEMS)
                .list();
    }

    public void writeStock() {
        Session session = sessionFactory.getCurrentSession();
        Item item = new Item();
        item.setName("MASERTA");
        item.setDate(new Date());

        Item item1 = new Item();
        item1.setName("BERGAT");
        item1.setDate(new Date());

        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item1);

        Stock stock = new Stock();
        stock.setName("SOVELENTO");
        stock.setCode("222%%%");

        item.setStock(stock);
        item1.setStock(stock);

        stock.setItems(items);

        session.save(stock);

        System.out.println("WRITED !!! ");
    }

}
