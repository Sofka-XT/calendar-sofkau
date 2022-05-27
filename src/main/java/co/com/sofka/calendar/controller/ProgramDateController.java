package co.com.sofka.calendar.controller;

import co.com.sofka.calendar.model.ProgramDate;
import co.com.sofka.calendar.repositories.ImplementProgramRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/programdate")
public class ProgramDateController {

    private static final Logger Log = LoggerFactory.getLogger(ProgramDateController.class);

    @Autowired
    private ImplementProgramRepository repo;

    @GetMapping("/listar")
    public Mono<ProgramDate> mostrar(){
        return Mono.just(new ProgramDate("Nueva categoria", LocalDate.of(2022,05,27)));
    }

    @PostMapping("registrar")
    public Mono<ProgramDate> registrar(@RequestBody ProgramDate program){
        return repo.registrar(program);
    }

}
