package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.exception.UsernameExistException;
import com.xeanco.xeanco.model.AppUser;
import com.xeanco.xeanco.security.LoginRequest;
import com.xeanco.xeanco.security.jwt.AuthenticationResponse;
import com.xeanco.xeanco.security.jwt.JwtUtil;
import com.xeanco.xeanco.service.AppUserService;
import com.xeanco.xeanco.service.ErrorHandlerService;
import com.xeanco.xeanco.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("")
public class UserController {
    @Autowired
    AppUserService appUserService;
    @Autowired
    ErrorHandlerService errorHandlerService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/users")
    public ResponseEntity<List<AppUser>>getUsers(){
        return ResponseEntity.ok().body(appUserService.getUsers());
    }

    @PostMapping("api/register")
    public ResponseEntity<?>saveUsers(@Valid @RequestBody AppUser appUser, BindingResult result){
        //Validate password Match
        userValidator.validate(appUser, result);
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null) return errorMap;
        AppUser appUser1 = appUserService.saveUser(appUser);
                return new ResponseEntity<AppUser>(appUser1, HttpStatus.CREATED);
    }

    @GetMapping("admin/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username){
        AppUser appUser1 = appUserService.getUser(username);
        return new ResponseEntity<AppUser>(appUser1, HttpStatus.OK);
    }

    @DeleteMapping("api/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        appUserService.deleteUser(username);
        return new ResponseEntity<String>("Username "+ username +" was deleted successfully", HttpStatus.OK);
    }


    @PostMapping("api/login")
    public ResponseEntity<?> generateToken(@Valid @RequestBody LoginRequest authRequest, BindingResult result) throws Exception {

        try {
            ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
            if(errorMap != null) return errorMap;
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new UsernameExistException("invalid username/password");
        }
        final UserDetails userDetails = appUserService
                .loadUserByUsername(authRequest.getUsername());
        final String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

/*
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<Role>addRole(@RequestBody RoleToUserForm form){
        appUserService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/addToUser")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                AppUser user = appUserService.getUser(username);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                stream(roles).forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role));
                });
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }catch(Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
           throw new RuntimeException("Refresh token is missing");
        }

    }*/
}
