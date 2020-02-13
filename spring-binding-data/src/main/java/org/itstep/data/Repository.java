package org.itstep.data;

import java.util.List;

public interface Repository<T, ID> {
    ID save(T data);
    void update(T data);
    boolean delete(T data);
    List<T> findAll();
    T find(ID id);
}
