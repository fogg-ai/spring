package org.itstep.init;

import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.model.Teacher;
import org.itstep.service.AcademyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Profile("dev")
@Component
public class InitDatabase {
    final AcademyService academyService;

    private static boolean inited;

    public InitDatabase(AcademyService academyService) {
        this.academyService = academyService;
    }

    @PostConstruct
    public void init() {
        if(inited) return;

        // List of groups
        List<Group> groups = List.of(
                new Group("Java summer 2019"),
                new Group("Java summer 2018"),
                new Group("Java summer 2017"),
                new Group("Internet Marketing 2018"),
                new Group("Java summer 2011"),
                new Group("Java summer 2016"),
                new Group("Java summer 2020"),
                new Group("Web autumn 2011"),
                new Group("Web autumn 2012"),
                new Group("Web autumn 2013"),
                new Group("Web autumn 2014"),
                new Group("Web autumn 2015"),
                new Group("Web autumn 2016"),
                new Group("Web autumn 2017"),
                new Group("Web autumn 2018")
        );
        // Init groups
        groups.forEach(academyService::save);

        // Init students
        academyService.save(new Student("Вася", "Пупкин",
                            LocalDate.of(2001, 1, 1), groups.get(0)));

        academyService.save(new Student("Маша", "Ефросинина",
                LocalDate.of(1986, 2, 12), groups.get(0)));



        academyService.save(new Teacher(null, "Василий", "Петрович",
                List.of(groups.get(0), groups.get(1))));
        academyService.save(new Teacher(null, "Василий", "Генадич",
                List.of(groups.get(0), groups.get(1))));
        academyService.save(new Teacher(null, "Григорий", "Николаевич",
                List.of(groups.get(0), groups.get(1))));
        academyService.save(new Teacher(null, "Алексей", "Петрович",
                List.of(groups.get(0), groups.get(1))));
        academyService.save(new Teacher(null, "Алексей", "Дмитривеч",
                List.of(groups.get(0), groups.get(1))));
        academyService.save(new Teacher(null, "Алексей", "Петрович",
                List.of(groups.get(2), groups.get(3))));
        academyService.save(new Teacher(null, "Дмитрий", "Петрович",
                List.of(groups.get(0), groups.get(1))));

        inited = true;
    }
}
