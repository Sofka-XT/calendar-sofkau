package co.com.sofka.calendar.repositories;

import co.com.sofka.calendar.collections.Program;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProgramRepository extends ReactiveMongoRepository<Program, String> {

}