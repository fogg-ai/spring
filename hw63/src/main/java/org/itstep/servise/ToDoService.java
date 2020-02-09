package org.itstep.servise;

import org.itstep.ToDo;
import org.itstep.bl.Util;
import org.itstep.db.ToDoDAO;
import sun.jvm.hotspot.debugger.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoService extends Util implements ToDoDAO {
    Connection connection = getConnection();

    public static ToDoService getInstance() {
        return new ToDoService();
    }

    @Override
    public void add(ToDo toDo) {
        PreparedStatement preparedStatement = null;

        int id = 1;
        boolean check = true;
        String select = "Select id,name from categories ORDER BY id";
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(select);
            if (resultSet == null) {
                id = 1;
            } else {
                while (resultSet.next()) {
                    if (toDo.getCategoryName().equals(resultSet.getString("name"))) {
                        check = false;
                        System.out.println(id + " SELECT");
                        break;
                    } else {
                        ++id;
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insert = "INSERT INTO categories (id,name) values(?,?)";
        try {
            preparedStatement = connection.prepareStatement(insert);
                if (check) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, toDo.getCategoryName());
                    preparedStatement.executeUpdate();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO todolist (short_description,long_description,created,start,end,category_id) values(?,?, CURTIME(),?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, toDo.getShortDescription());
            preparedStatement.setString(2, toDo.getLongDescription());
            preparedStatement.setString(3, toDo.getStart());
            preparedStatement.setString(4, toDo.getEnd());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSql(preparedStatement);
        }
    }

    @Override
    public List<ToDo> getAll() {
        List<ToDo> list = new ArrayList<>();

        String sql = "Select id,short_description,long_description,created,start,end from todolist";
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ToDo toDo = new ToDo();

                toDo.setId(resultSet.getInt("id"));
                toDo.setShortDescription(resultSet.getString("short_description"));
                toDo.setLongDescription(resultSet.getString("long_description"));
                toDo.setCreate(resultSet.getString("created"));
                toDo.setStart(resultSet.getString("start"));
                toDo.setEnd(resultSet.getString("end"));

                list.add(toDo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSql(statement);
        }
        return list;
    }

    @Override
    public void remove(int id) {
        PreparedStatement preparedStatement = null;


        String sqlC = "Delete from categories where id=?";
        try {
            preparedStatement = connection.prepareStatement(sqlC);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "Delete from todolist where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSql(preparedStatement);
        }
    }

    private void closeSql(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeSql(Statement Statement) {
        if (Statement != null) {
            try {
                Statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
