package e.hospital.lims.service;

import e.hospital.lims.dao.UserDao;
import e.hospital.lims.domain.SystemRole;
import e.hospital.lims.domain.User;
import e.hospital.lims.model.UserRequestModel;
import e.hospital.lims.model.UserResponseModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String SECRET = "secret";

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String generateAccessToken(String username, SystemRole role) {
        return generateToken(username, 1800, role);
    }

    @Override
    public String generateRefreshToken(String username, SystemRole role) {
        long refreshTime = 3000;
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
    public UserResponseModel register(UserRequestModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        try {
            userDao.save(user);
        } catch (Exception e) {
            throw new BadCredentialsException("");
        }
        return UserResponseModel
                .from(generateAccessToken(model.getUsername(),model.getLoginAs())
                        ,generateRefreshToken(model.getUsername(),model.getLoginAs()));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(String subject, long expirationInSeconds, SystemRole roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Role", roles);
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationInSeconds * 1000))
                .compact();
    }
}
