package com.ecommerce.services;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.models.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    public User findUserById(Long id) {
        User opt = userRepository.findById(id).get();
        return opt;
    }

    public  User findUserByJwt(String  jwt) throws Exception{
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if(email==null){
            throw new Exception("Provide valid jwt");
        }
        else{
            User user = userRepository.findByEmail(email);
            if(user == null){
                throw new Exception("user not found");
            } else{
                return user;
            }
        }
    }


}
