package dev.seabolt.springBootGradebook.repo;

import dev.seabolt.springBootGradebook.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends CrudRepository<TeacherEntity, Long>, PagingAndSortingRepository<TeacherEntity, Long> {
}
