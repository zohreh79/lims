package e.hospital.lims.config;

import e.hospital.lims.dao.DoctorDao;
import e.hospital.lims.dao.UserDao;
import e.hospital.lims.domain.Doctor;
import e.hospital.lims.domain.User;
import e.hospital.lims.model.CurrentUser;
import e.hospital.lims.service.AuthenticationService;
import e.hospital.lims.service.Errors.NotFound;
import e.hospital.lims.service.Errors.Unauthorized;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SecurityInterceptorHandler implements HandlerInterceptor {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DoctorDao doctorDao;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return intercept(request, response, handler);
    }


    private boolean intercept(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String refreshToken = request.getHeader("Refresh");
        if (accessToken != null && refreshToken !=null) {
//            if (authenticationService.isTokenValid(accessToken)) {
            Claims tokenClaims = authenticationService.getAllClaimsFromToken(accessToken);
            User user = userDao.findUserByUsername(tokenClaims.get("User").toString());
            if (user == null) throw new NotFound("User not found!");

            Doctor doctor = doctorDao.findByUser(user);
            if (doctor == null) throw new NotFound("not found!");

            currentUser.setRole(user.getRole());
            currentUser.setDoctorId(doctor.getDoctorId());
//            } else throw new Unauthorized("access to this path needs valid jwt token , please login again");
        } else throw new Unauthorized("access to this path needs jwt access and refresh token");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}