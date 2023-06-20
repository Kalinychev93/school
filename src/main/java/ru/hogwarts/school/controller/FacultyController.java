package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/faculty/12
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty =  facultyService.findFaculty(id);
        if(faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculties(){
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }


    @PostMapping // POST http://localhost:8080/faculty
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }
    @PutMapping // PUT http://localhost:8080/faculty
    public ResponseEntity<Student>  editFaculty(@RequestBody Faculty faculty){
        Fucalty foundFaculty = facultyService.editFaculty(faculty);
        if(foundFaculty == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("{id}") // DELETE http://localhost:8080/faculty/12
    public Faculty deleteFaculty(@PathVariable Long id){
        return facultyService.deleteFaculty(id);
    }
}