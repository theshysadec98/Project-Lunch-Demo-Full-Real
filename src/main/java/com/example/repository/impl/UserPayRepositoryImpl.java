package com.example.repository.impl;

import com.example.model.entity.UserPay;
import com.example.repository.BaseRepository;
import com.example.repository.custom.UserPayRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class UserPayRepositoryImpl extends BaseRepository implements UserPayRepositoryCustom {

    @Override
    public Page<UserPay> findAllUserByPriceWithDate(Timestamp timeStart, Timestamp timeFinal, Pageable pageable) {
        Query resultQuery = getAllUserByPriceWithDate(timeStart, timeFinal);
        List<Object[]> result = resultQuery.getResultList();
        List<UserPay> userPayList = new ArrayList<>();
        Iterator it =result.iterator();
        while (it.hasNext()){
            Object[] line = (Object[]) it.next();
            UserPay lastUser = new UserPay( line[0] != null ? (String)line[0] : null, line[1] != null ? ((BigInteger)line[1]).longValue() : null);
            userPayList.add(lastUser);
        }
        Page<UserPay> page =new PageImpl<>(userPayList,pageable, userPayList.size());
        return page;
    }

    @Override
    public Page<UserPay> findAllUserByPriceWithDateHQL(Timestamp timeStart, Timestamp timeFinal, Pageable pageable) {
        Query query = getAllUserByPriceWithDateHQL(timeStart, timeFinal);
        List<Object[]> result = query.getResultList();
        List<UserPay> userPayList = new ArrayList<>();
        Iterator it =result.iterator();
        while (it.hasNext()){
            Object[] line = (Object[]) it.next();
            UserPay lastUser = new UserPay( line[0] != null ? (String)line[0] : null, line[1] != null ? ((BigInteger)line[1]).longValue() : null);
            userPayList.add(lastUser);
        }
        userPayList.forEach(System.out::println);
        Page<UserPay> page = new PageImpl<>(userPayList, pageable, userPayList.size());
        return page;
    }

    private Query getAllUserByPriceWithDate(Timestamp timeStart, Timestamp timeFinal){
        StringBuilder sql =new StringBuilder();
        sql.append("SELECT  cm.name, SUM(f.price)");
        sql.append("FROM users cm INNER JOIN booking bk ON cm.id = bk.user_id ");
        sql.append("INNER JOIN booking_food bk_f ON bk.id = bk_f.booking_id ");
        sql.append("INNER JOIN food f ON bk_f.food_id = f.id ");
        if(timeStart != null ){
            sql.append("WHERE  TO_CHAR(bk.created_date, 'YYYY-MM-DD') >= CONCAT(:time_before) ");
            if(timeFinal != null){
                sql.append("AND TO_CHAR(bk.created_date, 'YYYY-MM-DD') <= CONCAT(:time_after) ");
            }
        }
        else {
            if(timeFinal != null){
                sql.append("WHERE  TO_CHAR(bk.created_date, 'YYYY-MM-DD') <= CONCAT(:time_after) ");
            }
        }
        sql.append("GROUP BY cm.name ");

        Query query = entityManager.createNativeQuery(sql.toString());
        if(timeStart != null){
            query.setParameter("time_before", String.valueOf(timeStart));
        }
        if(timeFinal != null){
            query.setParameter("time_after", String.valueOf(timeFinal));
        }

        return query;
    }

    private Query getAllUserByPriceWithDateHQL(Timestamp timeStart, Timestamp timeFinal){
        StringBuilder sql =new StringBuilder();
        sql.append("SELECT u.name, SUM(f.price) ");
        sql.append("FROM User u  JOIN u.booking bk ");
        sql.append("JOIN bk.food f");
        if(timeStart != null ){
            sql.append("WHERE  bk.created_date >= :time_before ");
            if(timeFinal != null){
                sql.append("AND bk.created_date <= :time_after ");
            }
        }
        else {
            if(timeFinal != null){
                sql.append("WHERE  bk.created_date <= :time_after ");
            }
        }
        sql.append("GROUP BY u.name ");

        Query query = entityManager.createQuery(sql.toString(), Object[].class);
        if(timeStart != null){
            query.setParameter("time_before", timeStart);
        }
        if(timeFinal != null){
            query.setParameter("time_after", timeFinal);
        }
        return query;
    }
}
