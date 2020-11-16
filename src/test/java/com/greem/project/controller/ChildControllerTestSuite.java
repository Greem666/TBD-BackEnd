package com.greem.project.controller;

import com.google.gson.Gson;
import com.greem.project.domain.child.ChildDto;
import com.greem.project.service.ChildService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ChildController.class)
public class ChildControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChildService childService;

    @Test
    public void shouldGetAllChildren() throws Exception {
        // Given
        List<ChildDto> childDtoList = new ArrayList<>();
        childDtoList.add(new ChildDto(1L, "test_name test_surname", "1-1-2011", "M"));

        when(childService.getAllChildren()).thenReturn(childDtoList);

        // When & Then
        mockMvc.perform(get("/v1/child"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].fullName", is("test_name test_surname")))
                .andExpect(jsonPath("$[0].dateOfBirth", is("1-1-2011")))
                .andExpect(jsonPath("$[0].gender", is("M")));
    }

    @Test
    public void shouldGetChild() throws Exception {
        // Given
        ChildDto childDto = new ChildDto(1L, "test_name test_surname", "1-1-2011", "M");

        when(childService.getChild(anyLong())).thenReturn(childDto);

        // When & Then
        mockMvc.perform(get("/v1/child/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fullName", is("test_name test_surname")))
                .andExpect(jsonPath("$.dateOfBirth", is("1-1-2011")))
                .andExpect(jsonPath("$.gender", is("M")));
    }

    @Test
    public void shouldAddChild() throws Exception {
        // Given
        ChildDto childDto = new ChildDto(1L, "test_name test_surname", "1-1-2011", "M");

        when(childService.addChild(ArgumentMatchers.any(ChildDto.class))).thenReturn(childDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(childDto);

        // When & Then
        mockMvc.perform(post("/v1/child/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fullName", is("test_name test_surname")))
                .andExpect(jsonPath("$.dateOfBirth", is("1-1-2011")))
                .andExpect(jsonPath("$.gender", is("M")));
        verify(childService, times(1)).addChild(ArgumentMatchers.any(ChildDto.class));
    }

    @Test
    public void shouldUpdateChild() throws Exception {
        // Given
        ChildDto childDto = new ChildDto(1L, "test_name test_surname", "1-1-2011", "M");

        when(childService.updateChild(ArgumentMatchers.any(ChildDto.class))).thenReturn(childDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(childDto);

        // When & Then
        mockMvc.perform(put("/v1/child/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fullName", is("test_name test_surname")))
                .andExpect(jsonPath("$.dateOfBirth", is("1-1-2011")))
                .andExpect(jsonPath("$.gender", is("M")));
        verify(childService, times(1)).updateChild(ArgumentMatchers.any(ChildDto.class));
    }

    @Test
    public void shouldDeleteChild() throws Exception {
        // Given
        ChildDto childDto = new ChildDto(1L, "test_name test_surname", "1-1-2011", "M");

        when(childService.deleteChild(any())).thenReturn(childDto);

        // When & Then
        mockMvc.perform(delete("/v1/child/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fullName", is("test_name test_surname")))
                .andExpect(jsonPath("$.dateOfBirth", is("1-1-2011")))
                .andExpect(jsonPath("$.gender", is("M")));
        verify(childService, times(1)).deleteChild(ArgumentMatchers.any());
    }
}