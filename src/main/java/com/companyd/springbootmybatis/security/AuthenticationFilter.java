//package com.companyd.springbootmybatis.security;
//
//
//import com.companyd.springbootmybatis.service.UserService;
//import com.example.myappapiusers.model.LoginRequestModel;
//import com.example.myappapiusers.service.UserService;
//import com.example.myappapiusers.shared.UserDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//// check -> username-(email) password(encryptedPassword)로 데이터베이스에 저장되어 있는지 확인. 암호화된 내용끼리 비교 or 복호화해서 비교.
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private UserService userService;
//    private Environment env;
//
//    public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
//        this.userService = userService;
//        this.env = env;
//        super.setAuthenticationManager(authenticationManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        try {
//            LoginRequestModel creds = new ObjectMapper()
//                    .readValue(request.getInputStream(), LoginRequestModel.class);
//
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            creds.getEmail(),
//                            creds.getPassword(),
//                            new ArrayList<>()
//                    )
//            );
//        } catch (IOException io) {
//            throw new RuntimeException();
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//
//        String email = ((User)authResult.getPrincipal()).getUsername();
//        UserDto userDetail = userService.getUserDetailByEmail(email);
//
//        // generate token with userId(email)
//        // token expired_time
//        String token = Jwts.builder()
//                .setSubject(userDetail.getUserId())
//                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
//                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
//                .compact();
//
//        response.addHeader("token", token);
//        response.addHeader("userId", userDetail.getUserId());
//    }
//}
