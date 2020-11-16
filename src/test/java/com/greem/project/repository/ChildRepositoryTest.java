package com.greem.project.repository;

import com.greem.project.domain.child.Child;
import com.greem.project.domain.child.Gender;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChildRepositoryTest {

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void shouldGetAllChildren() {
        // Given
        Child childOne = new Child("test1", "test1", LocalDate.of(2019, 1, 1), Gender.MALE);
        Child childTwo = new Child("test2", "test2", LocalDate.of(2020, 2, 2), Gender.FEMALE);

        childRepository.saveAll(Arrays.asList(childOne, childTwo));

        // When
        List<Child> allChildren = childRepository.findAll();

        // Then
        Assert.assertEquals(2, allChildren.size());
        Assert.assertTrue(allChildren.contains(childOne));
        Assert.assertTrue(allChildren.contains(childTwo));

        // Clean-up
        for (Child child: allChildren) {
            try {
                childRepository.deleteById(child.getId());
            } catch (Exception e) {
                // Do nothing
            }
        }
    }

    @Test
    public void shouldGetSpecificChild() {
        // Given
        Child child = new Child("test1", "test1", LocalDate.of(2019, 1, 1), Gender.MALE);

        childRepository.save(child);

        // When
        Optional<Child> retrievedChildOne = childRepository.findById(child.getId());

        // Then
        Assert.assertTrue(retrievedChildOne.isPresent());
        Assert.assertEquals(retrievedChildOne.get(), child);

        // Clean-up
        childRepository.deleteById(child.getId());
    }
}