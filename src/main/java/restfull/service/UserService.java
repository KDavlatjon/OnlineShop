package restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restfull.entity.Users;
import restfull.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    Save
    public Users addCustomer(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

//    Find by id
    public Users getOne(Long id){
        return  userRepository.findById(id).orElse(null);
    }

//    Find by Name
    public  Users getByName(String name){
        return userRepository.findByName(name);
    }

//    Find All
    public List<Users> getList(){
        return userRepository.findAll();
    }

//    Check Users
//    public Long check(Users users){
//        Long customerId = null;
//        List<Users> usersList = userRepository.findAll();
//        for (Users users1 : usersList) {
//            if (users1.getName().equals(users.getName()) && users1.getPhone().equals(users.getPhone())) {
//                customerId = users1.getId();
//                return customerId;
//            }
//        }
//        return customerId;
//    }

//    Delete Users
    public void deleteOne(Long id){
        userRepository.deleteById(id);
    }



//    Check with user
    public boolean checkUserPhone(String phone){
        return userRepository.existsByPhone(phone);
    }

    public boolean checkPasswordLength(String password){
        return password.length() >= 4;
    }
}

