package restfull.Restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restfull.entity.Users;
import restfull.entity.LoginVM;
import restfull.repository.UserRepository;
import restfull.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerJwtController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public CustomerJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginVM loginVM){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getName(), loginVM.getPassword()));
        Users users = userRepository.findByName(loginVM.getName());
        if (users == null){
            throw new UsernameNotFoundException("Bunday Foydalanuvchi mavjud emas");
        }
        String token = jwtTokenProvider.createToken(users.getName(), users.getRoles());
        Map<Object, Object> map = new HashMap<>();
        map.put("username", users.getName());
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

}
