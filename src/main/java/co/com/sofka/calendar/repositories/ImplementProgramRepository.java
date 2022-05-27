package co.com.sofka.calendar.repositories;

import co.com.sofka.calendar.model.ProgramDate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ImplementProgramRepository implements ProgramRepository{

    @Override
    public Mono<ProgramDate> registrar(ProgramDate program){
        return Mono.just(program);
    }
    @Override
    public Flux<ProgramDate> listar(){
        return Flux.just();
    }


}
