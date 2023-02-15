package e.hospital.lims.service.impl;

import e.hospital.lims.dao.DoctorDao;
import e.hospital.lims.dao.UserDao;
import e.hospital.lims.domain.Doctor;
import e.hospital.lims.domain.SystemRole;
import e.hospital.lims.domain.User;
import e.hospital.lims.model.DrModel;
import e.hospital.lims.model.UserRequestModel;
import e.hospital.lims.model.UserResponseModel;
import e.hospital.lims.service.AuthenticationService;
import e.hospital.lims.service.Errors.BadRequest;
import e.hospital.lims.service.Errors.Forbidden;
import e.hospital.lims.service.Errors.NotFound;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String SECRET = "LIMS-SECRET";

    @Autowired
    private UserDao userDao;

    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String generateAccessToken(String username, SystemRole role) {
        return generateToken(username, 180000, role);
    }

    @Override
    public String generateRefreshToken(String username, SystemRole role) {
        long refreshTime = 300000;
        return generateToken(username, refreshTime, role);
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return claims.getExpiration().after(new Date());
        } catch (IllegalArgumentException | JwtException e) {
            return false;
        }
    }


    @Override
    public ResponseEntity<?> register(UserRequestModel model) {
        var user = User.builder()
                .username(model.getUsername())
                .password(passwordEncoder.encode(model.getPassword()))
                .role(model.getLoginAs())
                .build();
        try {
            userDao.save(user);
        } catch (Exception e) {
            throw new BadRequest("Username already exists!");
        }
        return ResponseEntity.ok(UserResponseModel
                .from(generateAccessToken(model.getUsername(), model.getLoginAs())
                        , generateRefreshToken(model.getUsername(), model.getLoginAs())));
    }

    @Override
    public ResponseEntity<?> profile(DrModel model) {
        User user = userDao.findUserByUsername(model.getUsername());
        if (user == null)
            throw new NotFound("User not found");
        var doctor = Doctor.builder()
                .name(model.getDrName())
                .user(user)
                .build();

        doctorDao.save(doctor);
        return new ResponseEntity<>("profile created", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(UserRequestModel model) {
        try {
            if (!model.getLoginAs().equals(SystemRole.PATHOBIOLOGIST) && !model.getLoginAs().equals(SystemRole.PHYSICIAN))
                throw new Forbidden("Role not allowed");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication == null) {
                throw new NotFound("Username or password not found");
            }
            return ResponseEntity.ok(UserResponseModel
                    .from(generateAccessToken(model.getUsername(), model.getLoginAs())
                            , generateRefreshToken(model.getUsername(), model.getLoginAs())));
        } catch (Exception e) {
            throw new NotFound("Username or password not found");
        }
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(String subject, long expirationInSeconds, SystemRole roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Role", roles);
        claims.put("User", subject);
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationInSeconds * 1000))
                .compact();
    }
}
