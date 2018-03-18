package com.revature.networkingassistant.services.AccountServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private AccountRepo accountRepo;

    public RegisterService() { }

    @Autowired
    public RegisterService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    //checks most emails, stole it from the internet
    private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    //8 character, one digit, one alpha
    private static final String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.{8,})";

    //ex 333-444-5555
    private static final String phoneRegex = "^[2-9]\\d{2}-\\d{3}-\\d{4}$";

    //5 digit USA zip code
    private static final String zipRegex = "^\\d{5}$";

    private static final String nameRegex = "^[a-zA-Z]+(([\\'\\,\\.\\-][a-zA-Z])?[a-zA-Z]*)*$\n";

    private static final int genRounds = 12;

    @Transactional
    public ResponseEntity<Account> registerAccount(JsonRequestBody<Account> jsonRequestBody) {
        try {
            //if email does not exist
            if (!accountRepo.existsByEmail(jsonRequestBody.getObject().getEmail())) {

                //verify the info
                assert (jsonRequestBody.getObject().getEmail().matches(emailRegex));
                assert (jsonRequestBody.getObject().getPasswordHash().matches(passwordRegex));
                assert (jsonRequestBody.getObject().getPhone().matches(phoneRegex));
                assert (jsonRequestBody.getObject().getAttachment() != null);
                assert (jsonRequestBody.getObject().getZipCode().matches(zipRegex));
                assert (jsonRequestBody.getObject().getBackground() != null);
                assert (jsonRequestBody.getObject().getFirstName().matches(nameRegex));
                assert (jsonRequestBody.getObject().getLastName().matches(nameRegex));

                //hash password
                jsonRequestBody.getObject().setPasswordHash(hashPassword(jsonRequestBody.getObject().getPasswordHash()));
                //create the account
                accountRepo.save(jsonRequestBody.getObject());
                return new ResponseEntity<>(accountRepo.save(jsonRequestBody.getObject()), HttpStatus.CREATED);
            }
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>((Account)null, HttpStatus.BAD_REQUEST);
        }
    }

    public static String hashPassword(String pw) {
        String salt = BCrypt.gensalt(genRounds);
        return BCrypt.hashpw(pw, salt);
    }

}
