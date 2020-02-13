package org.itstep.data;

import org.itstep.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class StudentRepository implements org.itstep.data.Repository<Student, Integer> {

    private final static List<Student> storage = new CopyOnWriteArrayList<>();

    @Override
    public Integer save(Student data) {
        storage.add(data);
        data.setId(storage.size());
        return data.getId();
    }

    @Override
    public void update(Student data) {
        Optional<Student> first = storage.stream().filter(s -> s.getId() == data.getId()).findFirst();
        System.out.println(first.get());
        if(first.isPresent()) {
            Student student = first.get();
            student.setFirstName(data.getFirstName());
            student.setLastName(data.getLastName());
            student.setAge(data.getAge());
            student.setGroup(data.getGroup());
        }
    }

    @Override
    public boolean delete(Student data) {
        return storage.removeIf(s -> s.getId() == data.getId());
    }

    @Override
    public List<Student> findAll() {
        return storage;
    }

    @Override
    public Student find(Integer id) {
        return storage.stream()
                      .filter(s -> s.getId() == id).findFirst().orElse(null);
    }
}
