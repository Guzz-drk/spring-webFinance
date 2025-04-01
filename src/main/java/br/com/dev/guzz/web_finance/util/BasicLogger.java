package br.com.dev.guzz.web_finance.util;

import br.com.dev.guzz.web_finance.infra.config.TokenService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class BasicLogger {

    private static final Gson gson = new GsonBuilder().create();

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @Before("execution(* br.com.dev.guzz.web_finance.user.controller..*(..)) || " +
            "execution(* br.com.dev.guzz.web_finance.transaction.controller..*(..)) || " +
            "execution(* br.com.dev.guzz.web_finance.category.controller..*(..))")
    public void printRequest(JoinPoint jp){
        String controllerAndMethod = getUsedControllerAndMethod(jp);
        String callerUser = getCallerUser();
        String json = gson.toJson(jp.getArgs());
        log.info("{} request: {} - User: {} ", controllerAndMethod, json, callerUser);
    }

    @AfterReturning(
            pointcut = "execution(* br.com.dev.guzz.web_finance.user.controller..*(..)) || " +
            "execution(* br.com.dev.guzz.web_finance.transaction.controller..*(..)) || " +
            "execution(* br.com.dev.guzz.web_finance.category.controller..*(..))",
            returning = "result")
    public void printResponse(JoinPoint jp, Object result){
        String controllerAndMethod = getUsedControllerAndMethod(jp);
        String callerUser = getCallerUser();
        String json = gson.toJson(result);
        log.info("{} response: {} - User: {} ", controllerAndMethod, json, callerUser);
    }

    private String getUsedControllerAndMethod(JoinPoint jp){
        String controller = jp.getTarget().getClass().getSimpleName();
        String method = jp.getSignature().getName();
        return controller.concat("/").concat(method);
    }

    private String getCallerUser() {
        String authorization = request.getHeader("Authorization");

        if (StringHelper.isNullOrEmpty(authorization)) return "Unidentified User";

        authorization = authorization.replace("Bearer ", "");

        String login = tokenService.validateToken(authorization);

        if (!StringHelper.isNullOrEmpty(login)) return login;

        return "Unidentified User";
    }

}
