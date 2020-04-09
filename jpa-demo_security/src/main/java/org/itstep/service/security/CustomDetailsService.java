package org.itstep.service.security;

import org.itstep.domain.Group;
import org.itstep.domain.Student;
import org.itstep.domain.Teacher;
import org.itstep.repository.StudentRepository;
import org.itstep.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomDetailsService implements UserDetailsService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Student student = studentRepository.findUserByStudentName(userName);
        Teacher teacher = teacherRepository.findUserByTeacherName(userName);
        if(student != null){
            return new User(student.getStudentName(),student.getPassword(),
                    AuthorityUtils.createAuthorityList(student.getRole()));
        }else if(teacher != null){
            return new User(teacher.getTeacherName(),teacher.getPassword(),
                    AuthorityUtils.createAuthorityList(teacher.getRole()));
        }else {
            return new User("admin","{noop}admin",
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        }
    }
}
