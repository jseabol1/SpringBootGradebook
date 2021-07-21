package dev.seabolt.springBootGradebook.controller;

import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Generic Base Class for a Spring Web RestController.
 * Extending classes should be annotated with @RestController
 * from the org.springframework.web.bind.annotation package
 *
 * It is also highly recommended that the extending class be annotated with @RequestMapping
 * from the same package. This defines a base path for all endpoints in that controller.
 * Failure to do so when using more than one extending class will cause a build failure
 * due to overlapping endpoints.
 *
 * Endpoints this provides
 *
 * GET / - Returns a full list of entities
 * POST / - Create a new entity
 * GET /GetByID/? - Gets entity with specified ID
 * DELETE /DeleteByID/? - Deletes entity with specified ID
 *
 * No PUT options are provided, updating entities must be done in the extending class
 *
 *
 * @param <T> The CrudRepository to be controlled
 * @param <E> The Entity represented by the repository
 */
@NoArgsConstructor
//@RestController
abstract class ControllerBase<E,T extends CrudRepository<E,Long>> {

    protected T repository;

    ControllerBase(T t) {
        this.repository = t;
    }


    @GetMapping
    List<E> list() {
        List<E> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @PostMapping
    E newAddress(@RequestBody E e) {
        return repository.save(e);
    }


    @GetMapping("/GetByID/{id}")
    E getByID(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("address not found"));
    }


    @DeleteMapping("/DeleteByID/{id}")
    void deleteAddress(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
