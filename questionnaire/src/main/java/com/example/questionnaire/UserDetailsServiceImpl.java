package com.example.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Admin user = adminDao.findUser(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User" + userName + "was not found in the database");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantedAuthorityList.add(authority);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserDetails userDetails = (UserDetails) new User(user.getName(), encoder.encode(user.getPassword()), grantedAuthorityList);

        return userDetails;
    }
}
