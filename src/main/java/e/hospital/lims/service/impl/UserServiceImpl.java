package e.hospital.lims.service.impl;

import e.hospital.lims.dao.UserDao;
import e.hospital.lims.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User userModel = userDao.findUserByUsername(username);
        if (userModel == null) {
            throw new RuntimeException("");
        }
        return userModel;
    }
}
