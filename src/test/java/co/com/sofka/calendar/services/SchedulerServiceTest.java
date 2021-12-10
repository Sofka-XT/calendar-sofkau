package co.com.sofka.calendar.services;

import co.com.sofka.calendar.collections.CourseTime;
import co.com.sofka.calendar.collections.Program;
import co.com.sofka.calendar.collections.Time;
import co.com.sofka.calendar.model.ProgramDate;
import co.com.sofka.calendar.repositories.ProgramRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SchedulerServiceTest {

    @InjectMocks
    SchedulerService schedulerService;

    @Mock
    ProgramRepository repository;


    @Test
        //TODO: modificar el test para que el act sea reactivo, usando stepverifier
    void generateCalendar() {
        var programId = "xxxx";
        var startDate = LocalDate.of(2022, 1, 1);

        Program program = getProgramDummy();

        Mockito.when(repository.findById(programId)).thenReturn(Mono.just(program));
        //TODO: hacer una subscripción de el servicio reactivo
        List<ProgramDate> response = schedulerService.generateCalendar(programId, startDate);

        Assertions.assertEquals(13, response.size());//TODO: hacer de otro modo
        Assertions.assertEquals(getSnapResult(), new Gson().toJson(response));//TODO: hacer de otro modo
        Mockito.verify(repository).findById(programId);
    }

    @Test
    void programNoFound() {
        var programId = "xxxx";
        var startDate = LocalDate.of(2022, 1, 1);

        Mockito.when(repository.findById(programId)).thenReturn(Mono.empty());

        //TODO: hacer de otro modo
        var exception = Assertions.assertThrows(RuntimeException.class, () -> {
            schedulerService.generateCalendar(programId, startDate);//TODO: hacer una subscripción de el servicio reactivo

        });
        Assertions.assertEquals("El programa academnico no existe", exception.getMessage());//TODO: hacer de otro modo
        Mockito.verify(repository).findById(programId);

    }

    //no tocar
    private Program getProgramDummy() {
        var program = new Program();
        program.setId("xxxx");
        program.setName("Programa 2022");
        program.setCourses(new ArrayList<>());
        var timesForCourse1 = new ArrayList<Time>();
        timesForCourse1.add(new Time("1", 2, "Principios", List.of()));
        timesForCourse1.add(new Time("2", 2, "Bases", List.of()));
        timesForCourse1.add(new Time("3", 4, "Fundamentos", List.of()));
        timesForCourse1.add(new Time("3", 5, "Fundamentos avazandos", List.of()));

        program.getCourses().add(new CourseTime("xxx-z", "Introducción", timesForCourse1));
        return program;
    }

    //no tocar
    private String getSnapResult() {
        return "[{\"categoryName\":\"Principios\",\"date\":{\"year\":2022,\"month\":1,\"day\":3}},{\"categoryName\":\"Principios\",\"date\":{\"year\":2022,\"month\":1,\"day\":4}},{\"categoryName\":\"Bases\",\"date\":{\"year\":2022,\"month\":1,\"day\":5}},{\"categoryName\":\"Bases\",\"date\":{\"year\":2022,\"month\":1,\"day\":6}},{\"categoryName\":\"Fundamentos\",\"date\":{\"year\":2022,\"month\":1,\"day\":7}},{\"categoryName\":\"Fundamentos\",\"date\":{\"year\":2022,\"month\":1,\"day\":10}},{\"categoryName\":\"Fundamentos\",\"date\":{\"year\":2022,\"month\":1,\"day\":11}},{\"categoryName\":\"Fundamentos\",\"date\":{\"year\":2022,\"month\":1,\"day\":12}},{\"categoryName\":\"Fundamentos avazandos\",\"date\":{\"year\":2022,\"month\":1,\"day\":13}},{\"categoryName\":\"Fundamentos avazandos\",\"date\":{\"year\":2022,\"month\":1,\"day\":14}},{\"categoryName\":\"Fundamentos avazandos\",\"date\":{\"year\":2022,\"month\":1,\"day\":17}},{\"categoryName\":\"Fundamentos avazandos\",\"date\":{\"year\":2022,\"month\":1,\"day\":18}},{\"categoryName\":\"Fundamentos avazandos\",\"date\":{\"year\":2022,\"month\":1,\"day\":19}}]";
    }


}