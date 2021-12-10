package co.com.sofka.calendar.services;

import co.com.sofka.calendar.collections.Program;
import co.com.sofka.calendar.model.ProgramDate;
import co.com.sofka.calendar.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Service
public class SchedulerService {

    @Autowired
    private ProgramRepository programRepository;

    //TODO: deben retornar un flux de programDate Flux<ProgramDate>
    public List<ProgramDate> generateCalendar(String programId, LocalDate startDate) {
        var endDate = new AtomicReference<>(LocalDate.from(startDate));
        final AtomicInteger[] pivot = {new AtomicInteger()};
        final int[] index = {0};

        //TODO: debe pasarlo a reactivo, no puede trabaja elementos bloqueantes
        //TODO: trabajar el map reactivo y no deben colectar
        var program = programRepository.findById(programId).block();
        return Optional.ofNullable(program)
                .map(this::getDurationOf)
                .orElseThrow(() -> new RuntimeException("El programa academnico no existe"))
                .map(toProgramDate(startDate, endDate, pivot[0], index))
                .collect(Collectors.toList());
    }

    //No tocar
    private Function<String, ProgramDate> toProgramDate(LocalDate startDate, AtomicReference<LocalDate> endDate, AtomicInteger atomicInteger, int[] index) {
        return category -> {
            var increment = endDate.get().getDayOfWeek().getValue() > 5
                    ? 8 - endDate.get().getDayOfWeek().getValue()
                    : 0;

            atomicInteger.set(atomicInteger.get() + increment);
            endDate.set(LocalDate.from(endDate.get().plusDays(1 + increment)));
            var result = startDate.plusDays(index[0] + atomicInteger.get());
            index[0]++;
            return new ProgramDate(category, result);
        };
    }

    //No tocar
    private Stream<String> getDurationOf(Program program) {
        return program.getCourses().stream()
                .flatMap(courseTime -> courseTime.getCategories().stream())
                .flatMap(time -> IntStream.range(0, time.getDays()).mapToObj(i -> time.getCategoryName()));
    }

}
