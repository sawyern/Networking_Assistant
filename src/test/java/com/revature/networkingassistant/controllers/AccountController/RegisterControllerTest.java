package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.EventsController.CreateEventController;
import com.revature.networkingassistant.repositories.AccountRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
@Transactional
class RegisterControllerTest {

    @Test
    void registerAccountTest() {
        RegisterController controller = new RegisterController();
        byte[] test = "test".getBytes();
        Account account = new Account("test@test.test", "test", "test", "test", "test", test);
        JsonRequestBody<Account> requestBody = new JsonRequestBody<>();
        requestBody.setObject(account);
        Account testAccount = controller.registerAccount(requestBody);
        assertNotNull(testAccount);
    }
}