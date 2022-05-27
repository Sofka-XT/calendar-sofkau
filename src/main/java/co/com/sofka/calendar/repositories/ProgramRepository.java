package co.com.sofka.calendar.repositories;

import co.com.sofka.calendar.collections.Program;
import co.com.sofka.calendar.model.ProgramDate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProgramRepository /*extends ReactiveMongoRepository<Program, String>*/ {
    Mono<ProgramDate> registrar(ProgramDate programm);
    Flux<ProgramDate> listar();
}