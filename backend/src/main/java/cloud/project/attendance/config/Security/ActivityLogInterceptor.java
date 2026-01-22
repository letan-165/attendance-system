package cloud.project.attendance.config.Security;

import cloud.project.attendance.common.exception.AppException;
import cloud.project.attendance.entity.ActivityLog;
import cloud.project.attendance.repository.ActivityLogRepository;
import cloud.project.attendance.service.AuthService;
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
    ActivityLogRepository activityLogRepository;
    AuthService authService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (!(handler instanceof HandlerMethod handlerMethod))
            return true;

        String controllerName =
                handlerMethod.getBeanType().getSimpleName();

        String userId = "GUEST";
        try {
             userId = authService.getUserIdFromToken();
        } catch (AppException ignored) {

        }

        ActivityLog log = ActivityLog.builder()
                .userId(userId)
                .action(controllerName)
                .endpoint(request.getMethod() + " " + request.getRequestURI())
                .build();

        activityLogRepository.save(log);
        return true;
    }
}

