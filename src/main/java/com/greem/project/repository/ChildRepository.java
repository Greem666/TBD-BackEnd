package com.greem.project.repository;

import com.greem.project.domain.child.Child;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ChildRepository extends CrudRepository<Child, Long> {

    @Override
    List<Child> findAll();
}
