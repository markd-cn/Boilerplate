package net.boblog.test.api;

import net.boblog.app.entity.Account;
import net.boblog.app.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by dave on 16/5/19.
 */
public class AccountControllerTest extends MvcTest {
    @Autowired AccountService accountService;

    @Before
    public void setup() {
        Account account = new Account();
        account.setUsername("test");
        account.setEncryptPassword(Account.encryptPassword("123456"));
        accountService.save(account);
    }

    @Test
    public void register() {
        given().log().all().
                webAppContextSetup(context).
                param("username", "admin0").
                param("password", "123456").
                when().
                post("/api/account/register").
                then().log().all().
                statusCode(200).
                body("result", equalTo(true));
    }

    @Test
    public void login() {
        given().log().all().
                webAppContextSetup(context).
                param("username", "test").
                param("password", "123456").
                when().
                post("/api/account/login").
                then().log().all().
                statusCode(200).
                body("result", equalTo(true));
    }

}
