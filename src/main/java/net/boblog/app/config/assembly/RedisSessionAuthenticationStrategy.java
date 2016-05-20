package net.boblog.app.config.assembly;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by dave on 16/5/19.
 */
public class RedisSessionAuthenticationStrategy implements SessionAuthenticationStrategy {
    private RedisOperationsSessionRepository sessionRepository;

    public RedisSessionAuthenticationStrategy(RedisOperationsSessionRepository repository) {
        sessionRepository = repository;
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response)
            throws SessionAuthenticationException {
        if (!authentication.isAuthenticated()) return;
        Map<String, ? extends ExpiringSession> sessions = sessionRepository.findByIndexNameAndIndexValue(
                FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
                authentication.getPrincipal().toString());
        if (sessions.size() > 1) throw new SessionAuthenticationException("你已登录,请不要重复登录");
    }
}
