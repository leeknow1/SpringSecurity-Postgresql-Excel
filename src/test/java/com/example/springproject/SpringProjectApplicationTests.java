package com.example.springproject;

import com.example.springproject.entity.EmployeeEntity;
import com.example.springproject.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class SpringProjectApplicationTests {
    String badRequest = "{\"username\": \"test\"}";
    String okRequest = "{\n" +
            "    \"lastname\": \"testing\",\n" +
            "    \"firstname\": \"testing\",\n" +
            "    \"fathername\": \"testing\",\n" +
            "    \"birthday\": \"1988-12-12T00:00:00\",\n" +
            "    \"id_position\": 1,\n" +
            "    \"id_rank\": 1,\n" +
            "    \"id_division\": 1,\n" +
            "    \"contract_conclusion\": \"2020-12-12T00:00:00\",\n" +
            "    \"contract_term\": 1\n" +
            "}";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getHomePageWith200() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void getEmployeeWith200byUser() throws Exception{
        mockMvc.perform(get("/employee/")
                .with(httpBasic("user","user123"))).andExpect(status().isOk());
    }

    @Test
    void getEmployeeWith200byAdmin() throws Exception{
        mockMvc.perform(get("/employee/")
                .with(httpBasic("admin","admin123"))).andExpect(status().isOk());
    }

    @Test
    void getIDEmployeeWith200() throws Exception{
        mockMvc.perform(get("/employee/1")
                .with(httpBasic("admin","admin123"))).andExpect(status().isOk());
    }

    @Test
    void getIDEmployeeWith403() throws Exception{
        mockMvc.perform(get("/employee/1")
                .with(httpBasic("user","user123"))).andExpect(status().isForbidden());
    }

    @Test
    void postEmployeeWith400() throws Exception{
        mockMvc.perform(post("/employee/").with(httpBasic("admin","admin123"))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(badRequest)).andExpect(status().isBadRequest());
    }

    @Test
    void postEmployeeWith200() throws Exception{
        mockMvc.perform(post("/employee/").with(httpBasic("admin","admin123"))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(okRequest)).andExpect(status().isOk());
    }

    @Test
    void putEmployeeWith200() throws Exception{
        int last = employeeService.getLast();
        mockMvc.perform(put("/employee/" + last).with(httpBasic("admin","admin123"))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(okRequest)).andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeWith200() throws Exception{
        int last = employeeService.getLast();
        mockMvc.perform(delete("/employee/" + last)
                .with(httpBasic("admin","admin123"))).andExpect(status().isOk());
    }


    @Test
    void getAllNotNull(){
        List<EmployeeEntity> employee = employeeService.getAll();
        Assertions.assertNotNull(employee);
    }

    @Test
    void employeeIDNotNull(){
        for( EmployeeEntity emp : employeeService.getAll()){
            if(emp.getId()==null){
                Assertions.fail(emp + ": employee id is null");
            }
        }
    }

    @Test
    void employeeLastNameLengthTest(){
        for( EmployeeEntity emp : employeeService.getAll()){
            if(emp.getLastname().isBlank() || emp.getLastname().length()<2 || emp.getLastname().length() > 20){
                Assertions.fail(emp + ": employee lastname size is incorrect");
            }
        }
    }

    @Test
    void employeeFirstNameLengthTest(){
        for( EmployeeEntity emp : employeeService.getAll()){
            if(emp.getFirstname().isBlank() || emp.getFirstname().length()<2 || emp.getFirstname().length() > 20){
                Assertions.fail(emp + ": employee firstname size is incorrect");
            }
        }
    }
    @Test
    void employeeFatherNameLengthTest(){
        for( EmployeeEntity emp : employeeService.getAll()){
            if(emp.getFathername().isBlank() || emp.getFathername().length()<2 || emp.getFathername().length() > 20){
                Assertions.fail(emp + ": employee fathername size is incorrect");
            }
        }
    }

    @Test
    void employeeBirthdayTest(){
        Date currentDate = new Date(System.currentTimeMillis());
        for( EmployeeEntity emp : employeeService.getAll()){
            if(emp.getBirthday().after(currentDate)){
                Assertions.fail(emp + ": employee birthday is in the future");
            }
        }
    }

    @Test
    void employeePositionRangeTest(){
        for(EmployeeEntity emp : employeeService.getAll()){
            if(emp.getId_position()<1 || emp.getId_position()>4){
                Assertions.fail(emp + ": employee position out of range");
            }
        }
    }

    @Test
    void employeeRankRangeTest(){
        for(EmployeeEntity emp : employeeService.getAll()){
            if(emp.getId_rank()<1 || emp.getId_rank()>7){
                Assertions.fail(emp + ": employee rank out of range");
            }
        }
    }

    @Test
    void employeeDivisionRangeTest(){
        for(EmployeeEntity emp : employeeService.getAll()){
            if(emp.getId_division()<1 || emp.getId_division()>3){
                Assertions.fail(emp + ": employee division out of range");
            }
        }
    }

    @Test
    void employeeContractConclusionTest(){
        Date currentDate = new Date(System.currentTimeMillis());
        for( EmployeeEntity emp : employeeService.getAll()){
            if(emp.getContract_conclusion().after(currentDate)){
                Assertions.fail(emp + ": employee contract is in the future");
            }
        }
    }

    @Test
    void employeeContractTermTest(){
        for(EmployeeEntity emp : employeeService.getAll()){
            if(emp.getContract_term()<=0){
                Assertions.fail(emp + ": employee contract term is expired");
            }
        }
    }

    @Test
    void NoSuchElementExceptionTest(){
        Assertions.assertThrows(NoSuchElementException.class, () -> employeeService.getById(0));
    }
}
