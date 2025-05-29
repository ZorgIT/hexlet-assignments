package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    private Task existingTask;

    @BeforeEach
    public void createTaskOjb() throws Exception {
        taskRepository.deleteAll();

        existingTask = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getUpdatedAt))
                .ignore(Select.field(Task::getCreatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.hobbit().thorinsCompany())
                .supply(Select.field(Task::getDescription),
                        () -> faker.hobbit().quote())
                .create();

        taskRepository.save(existingTask);
    }

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    //Просмотр конкретной задачи
    @Test
    public void showExistingTask() throws Exception {
        mockMvc.perform(get("/tasks/" + existingTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingTask.getId()))
                .andExpect(jsonPath("$.title").value(existingTask.getTitle()))
                .andExpect(jsonPath("$.description").value(existingTask.getDescription()));
    }

    //Создание новой задачи
    @Test
    public void createNewTask() throws Exception {
        var newTask = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getUpdatedAt))
                .ignore(Select.field(Task::getCreatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.hobbit().thorinsCompany())
                .supply(Select.field(Task::getDescription), () -> faker.hobbit().quote())
                .create();

        var response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        var responseTask = om.readValue(response.getContentAsString(), Task.class);
        var savedTask = taskRepository.findById(responseTask.getId()).orElseThrow();

        assertThat(savedTask.getTitle()).isEqualTo(newTask.getTitle());
        assertThat(savedTask.getDescription()).isEqualTo(newTask.getDescription());
    }

    //Обновление существующей задачи
    @Test
    public void updateExistingTask() throws Exception {
        var updatedData = new HashMap<>();
        updatedData.put("title", "Updated Title");
        updatedData.put("description", "Updated Description");

        mockMvc.perform(put("/tasks/" + existingTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updatedData)))
                .andExpect(status().isOk());

        var updatedTask = taskRepository.findById(existingTask.getId()).orElseThrow();
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedTask.getDescription()).isEqualTo("Updated Description");
    }

    //Удаление задачи
    @Test
    public void deleteExistingTask() throws Exception {
        mockMvc.perform(delete("/tasks/" + existingTask.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(existingTask.getId())).isEmpty();
    }

    // END
}
