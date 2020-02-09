package org.itstep.db;

import org.itstep.ToDo;

import java.util.List;

public interface ToDoDAO {
    void add(ToDo list);

    List<ToDo> getAll();

    void remove(int id);
}
