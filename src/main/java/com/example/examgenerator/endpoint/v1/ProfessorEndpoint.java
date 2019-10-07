package com.example.examgenerator.endpoint.v1;

import com.example.examgenerator.persistence.model.Professor;
import com.example.examgenerator.persistence.repository.ProfessorRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController //é uma anotação de conveniência que faz nada além de adicionar as anotações @Controllere @ResponseBody
@RequestMapping("/v1/professor") //Define a url que quando for requisitada chamara o metodo
public class ProfessorEndpoint {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorEndpoint(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping(path = "{id}") //uma notação composta que atua como um atalho para @RequestMapping (method = RequestMethod.GET).
    @ApiOperation(value = "Find professor by ID", notes = "We have to make this method better", response = Professor.class)
    public ResponseEntity<?>getProfessorById(@PathVariable long id) {  //representa toda a resposta HTTP: código de status, cabeçalhos e corpo .
        Optional<Professor> professor = professorRepository.findById(id);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }
}
