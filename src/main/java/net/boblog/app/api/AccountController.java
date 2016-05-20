package net.boblog.app.api;

import io.swagger.annotations.ApiOperation;
import net.boblog.app.entity.Account;
import net.boblog.app.external.JsonMessage;
import net.boblog.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * Created by dave on 16/5/19.
 */
@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired AccountService accountService;
    @Autowired SessionAuthenticationStrategy sessionAuthenticationStrategy;

    @ApiOperation(value = "用户登录", position = 0)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public JsonMessage<Account> login(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Account account;
        if (Pattern.matches("\\d{11}", username)) {
            account = accountService.findByPhone(username);
        } else if (Pattern.matches("[\\w.\\-]+@([\\w\\-]+\\.)+[a-zA-Z]+", username)) {
            account = accountService.findByEmail(username);
        } else {
            account = accountService.findByUsername(username);
        }

        if (account == null) {
            return new JsonMessage<>(false, "NO_RECORD", "没有此用户");
        } else if (!Account.encryptPassword(password).equals(account.getEncryptPassword())) {
            return new JsonMessage<>(false, "WRONG_PASSWORD", "密码错误");
        } else {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(account.getUsername(), null, null);
            auth.setDetails(account);
            SecurityContextHolder.getContext().setAuthentication(auth);
            sessionAuthenticationStrategy.onAuthentication(auth, request, response);
            return new JsonMessage<>(true, "", "", account);
        }
    }

    @ApiOperation(value = "用户注册", position = 1)
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public JsonMessage<Account> register(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        if (accountService.findByUsername(username) != null) {
            return new JsonMessage<>(false, "OCCUPIED_ID", "用户名已注册");
        } else if (!Pattern.matches("^[A-Za-z][0-9A-Za-z_]{5,}$", username)) {
            return new JsonMessage<>(false ,"USERNAME_PATTERN_MISMATCH", "用户名至少6个字符, 只能以字母开头, 只能使用数字和字母及下划线");
        } else if (password.length() < 6) {
            return new JsonMessage<>(false, "PASSWORD_TOO_SHORT", "密码至少6位");
        }

        Account account = new Account();
        account.setUsername(username);
        account.setEncryptPassword(Account.encryptPassword(password));
        accountService.save(account);
        return new JsonMessage<>(true, "", "", account);
    }

    @ApiOperation(value = "用户注销", position = 2)
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public JsonMessage<?> logout(@ApiIgnore HttpSession session) {
        session.invalidate();
        return new JsonMessage<>(true);
    }

    @ApiOperation(value = "获取帐户信息", position = 3)
    @RequestMapping(method = RequestMethod.GET)
    public JsonMessage<Account> logout() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        Account account = Account.class.isInstance(details) ? (Account) details : null;
        return new JsonMessage<>(true, "", "", account);
    }
}
