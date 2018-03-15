package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.RequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    SessionTokenRepo sessionTokenRepo;

    //checks most emails, stole it from the internet
    private final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    //8 character, one digit, one alpha
    private final String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.{8,})";

    //ex 333-444-5555
    private final String phoneRegex = "^[2-9]\\d{2}-\\d{3}-\\d{4}$";

    //5 digit USA zip code
    private final String zipRegex = "^\\d{5}$";

    private final int genRounds = 12;

    @Transactional
    @RequestMapping(value = "/api/register", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account registerAccount(@org.springframework.web.bind.annotation.RequestBody RequestBody<Account> requestBody) {
        System.out.println(requestBody.getObject());
        if (requestBody == null || requestBody.getObject() == null)
            return null;

        //if email does not exist
        if (!accountRepo.existsByEmail(requestBody.getObject().getEmail())) {

            //verify the info
            assert(requestBody.getObject().getEmail().matches(emailRegex));
            assert(requestBody.getObject().getPasswordHash().matches(passwordRegex));
            assert(requestBody.getObject().getPhone().matches(phoneRegex));
            assert(requestBody.getObject().getAttachment() != null);
            assert(requestBody.getObject().getZipCode().matches(zipRegex));
            assert(requestBody.getObject().getBackground() != null);

            //hash password
            requestBody.getObject().setPasswordHash(hashPassword(requestBody.getObject().getPasswordHash()));
            //create the account
            return accountRepo.save(requestBody.getObject());
        }
        return null;
    }

    public String hashPassword(String pw) {
        String salt = BCrypt.gensalt(genRounds);
        return BCrypt.hashpw(pw, salt);
    }
}
