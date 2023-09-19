package de.ait.task_03.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();

    void save(T model);
}
