package com.etnetera.hr.controller;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.mapper.FrameworkMapper;
import com.etnetera.hr.model.JavaScriptFrameworkDto;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
@RequestMapping("api/v1")
public class JavaScriptFrameworkController {

    private final JavaScriptFrameworkRepository repository;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/frameworks")
    public ResponseEntity<Iterable<JavaScriptFramework>> frameworks() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/frameworks/{id}")
    public ResponseEntity<JavaScriptFrameworkDto> getFramework(@PathVariable Long id) {
        Optional<JavaScriptFramework> framework = repository.findById(id);
        return framework.map(javaScriptFramework -> ResponseEntity.status(HttpStatus.OK).body(FrameworkMapper.INSTANCE.frameworkToFrameworkDto(javaScriptFramework))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/frameworks")
    public ResponseEntity<JavaScriptFrameworkDto> addNewFramework(@RequestBody JavaScriptFrameworkDto frameworkDto) {
        JavaScriptFrameworkDto framework = FrameworkMapper.INSTANCE.frameworkToFrameworkDto(repository.save(FrameworkMapper.INSTANCE.frameworkDtoToFramework(frameworkDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(framework);
    }

    @PutMapping("/frameworks/{id}")
    public ResponseEntity<JavaScriptFrameworkDto> updateFramework(@PathVariable Long id, @RequestBody JavaScriptFrameworkDto frameworkDto) {

        Optional<JavaScriptFramework> frameworkOptional = repository.findById(id);

        if (frameworkOptional.isPresent()) {
            JavaScriptFramework framework = frameworkOptional.get();
            framework.setName(frameworkDto.getName());
            framework.setHypeLevel(frameworkDto.getHypeLevel());
            if (frameworkDto.getVersions() != null) {
                framework.getVersions().addAll(frameworkDto.getVersions());
            }
            JavaScriptFrameworkDto updatedFramework = FrameworkMapper.INSTANCE.frameworkToFrameworkDto(repository.save(framework));
            return ResponseEntity.status(HttpStatus.OK).body(updatedFramework);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/frameworks/{id}")
    public ResponseEntity<String> deleteFramework(@PathVariable Long id) {
        Optional<JavaScriptFramework> framework = repository.findById(id);
        if (framework.isPresent()) {
            repository.delete(framework.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
