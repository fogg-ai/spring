package org.itstep.dao;

import java.util.List;

public interface Dao<T, ID> {
    ID save(T data);
    void update(T data);
    boolean delete(T data);
    List<T> findAll();
    T getOne(ID id);
    boolean deleteById(ID id);
}
