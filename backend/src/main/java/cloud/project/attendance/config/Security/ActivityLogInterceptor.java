package cloud.project.attendance.config.Security;

import cloud.project.attendance.common.enums.UserRole;
import cloud.project.attendance.common.exception.AppException;
import cloud.project.attendance.entity.ActivityLog;
import cloud.project.attendance.entity.AdminLog;
import cloud.project.attendance.service.AuthService;
import cloud.project.attendance.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActivityLogInterceptor implements HandlerInterceptor {
    LogService logService;
    AuthService authService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) {

        if (!(handler instanceof HandlerMethod handlerMethod))
            return;

        String controllerName = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();
        String action = controllerName.replace("Controller",  ".") + methodName;

        String endpoint = response.getStatus()+ "-" +request.getMethod() + " " + request.getRequestURI();

        if (controllerName.equals("HealthController")) {
            logService.adminSaveHealth(AdminLog.builder()
                    .action(action)
                    .endpoint(endpoint)
                    .build());
        }else{
            String userId = UserRole.GUEST.name();
            try {
                userId = authService.getUserIdFromToken();
            } catch (AppException ignored) {}

            logService.userSaveAction(ActivityLog.builder()
                    .userId(userId)
                    .action(action)
                    .endpoint(endpoint)
                    .build());
        }
    }
}

