package com.greem.project.mapper;

import com.greem.project.domain.child.Child;
import com.greem.project.domain.child.ChildDto;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChildMapperTestSuite {

    @Autowired
    private ChildMapper childMapper;


    @Test
    public void shouldMapToChildDto() {
        // Given
        Child child = new Child(999L, "test_name", "test_surname", LocalDate.of(2011, 1, 1), Gender.MALE);

        // When
        ChildDto childDto = childMapper.mapToChildDto(child);

        // Then
        Assert.assertEquals(child.getId(), childDto.getId());
        Assert.assertEquals("test_name", childDto.getFullName().split(" ")[0]);
        Assert.assertEquals("test_surname", childDto.getFullName().split(" ")[1]);
        Assert.assertEquals("1-1-2011", childDto.getDateOfBirth());
        Assert.assertEquals("M", childDto.getGender());
    }

    @Test
    public void shouldMapToChild() {
        // Given
        ChildDto childDto = new ChildDto(666L,"John Doe", "1-1-2011", "M");

        // When
        Child child = childMapper.mapToChild(childDto);

        // Then
        Assert.assertEquals(childDto.getId(), child.getId());
        Assert.assertEquals("John", child.getFirstName());
        Assert.assertEquals("Doe", child.getLastName());
        Assert.assertEquals(1, child.getDateOfBirth().getDayOfMonth());
        Assert.assertEquals(1, child.getDateOfBirth().getMonthValue());
        Assert.assertEquals(2011, child.getDateOfBirth().getYear());
        Assert.assertEquals(Gender.MALE, child.getGender());
    }

    @Test
    public void shouldMapToChildDtoList() {
        // Given
        Child childOne = new Child(333L, "test_name1", "test_surname1", LocalDate.of(2011, 1, 1), Gender.MALE);
        Child childTwo = new Child(444L, "test_name2", "test_surname2", LocalDate.of(2011, 2, 2), Gender.OTHER);

        List<Child> allChildren = Arrays.asList(childOne, childTwo);

        // When
        List<ChildDto> mappedChildren = childMapper.mapToChildDtoList(allChildren);
        ChildDto retrievedChildOne = mappedChildren.get(0);
        ChildDto retrievedChildTwo = mappedChildren.get(1);

        // Then
        Assert.assertEquals(childOne.getId(), retrievedChildOne.getId());
        Assert.assertEquals("test_name1", retrievedChildOne.getFullName().split(" ")[0]);
        Assert.assertEquals("test_surname1", retrievedChildOne.getFullName().split(" ")[1]);
        Assert.assertEquals("1-1-2011", retrievedChildOne.getDateOfBirth());
        Assert.assertEquals("M", retrievedChildOne.getGender());

        Assert.assertEquals(childTwo.getId(), retrievedChildTwo.getId());
        Assert.assertEquals("test_name2", retrievedChildTwo.getFullName().split(" ")[0]);
        Assert.assertEquals("test_surname2", retrievedChildTwo.getFullName().split(" ")[1]);
        Assert.assertEquals("2-2-2011", retrievedChildTwo.getDateOfBirth());
        Assert.assertEquals("O", retrievedChildTwo.getGender());
    }
}