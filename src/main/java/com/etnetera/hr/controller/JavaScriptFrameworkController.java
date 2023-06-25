package com.etnetera.hr.controller;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.exception.FrameworkNotFoundException;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
public class JavaScriptFrameworkController {

    private final JavaScriptFrameworkRepository repository;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> frameworks() {
        return repository.findAll();
    }

    @GetMapping("/frameworks/{id}")
    public JavaScriptFramework getFramework(@PathVariable Long id) throws FrameworkNotFoundException {
        return repository.findById(id).orElseThrow(() -> new FrameworkNotFoundException(String.format("Framework id %s not found.", id)));
    }

    @PostMapping("/frameworks")
    public JavaScriptFramework addNewFramework(@RequestBody JavaScriptFramework framework) {
        return repository.save(framework);
    }

}
