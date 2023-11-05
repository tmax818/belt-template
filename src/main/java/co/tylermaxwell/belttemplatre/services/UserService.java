package co.tylermaxwell.belttemplatre.services;

import co.tylermaxwell.belttemplatre.models.LoginUser;
import co.tylermaxwell.belttemplatre.models.User;
import co.tylermaxwell.belttemplatre.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User register(User newUser, BindingResult result) {


        // Reject if email is taken (present in database)
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()){
            result.rejectValue("email", "Email", "What are you doing!!! You already regestered");
        }

        //Reject if password doesn't match confirmation
        if(!newUser.getPassword().equals(newUser.getConfirm())){
            result.rejectValue("password", "Password", "Dude, learn to type!!! Your passwords don't match!!");
        }

        //Return null if result has errors
        if(result.hasErrors()){
            return null;
        }

        //Hash password
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());

        //set password
        newUser.setPassword(hashed);

        //save user to database
        return userRepository.save(newUser);
    }

    public User login(LoginUser newLoginObject, BindingResult result) {


        //Find user in the DB by email
        Optional<User> user = userRepository.findByEmail(newLoginObject.getEmail());

        //Reject if NOT present
        //Reject if BCrypt password match fails
        if(!user.isPresent()){
            result.rejectValue("email", "logEmail", "invalid credentials");
        } else if (!BCrypt.checkpw(newLoginObject.getPassword(), user.get().getPassword())){
            result.rejectValue("password", "logPassword", "invalid credentials");
        }

        //TReturn null if result has errors
        if(result.hasErrors()){
            return null;
        }

        //Otherwise, return the user object
        return user.get();
    }
}
