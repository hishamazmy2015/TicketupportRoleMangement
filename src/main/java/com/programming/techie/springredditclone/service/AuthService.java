package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.AuthenticationResponse;
import com.programming.techie.springredditclone.dto.LoginRequest;
import com.programming.techie.springredditclone.dto.RegisterRequest;
import com.programming.techie.springredditclone.model.Token;
import com.programming.techie.springredditclone.model.User;
import com.programming.techie.springredditclone.repository.TokenDto;
import com.programming.techie.springredditclone.repository.UserRepository;
import com.programming.techie.springredditclone.repository.VerificationTokenRepository;
import com.programming.techie.springredditclone.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    private final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final HandleUtilityService handleUtilityService;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUsername(registerRequest.getUsername());
        user.setRole(registerRequest.getRole());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name is not found - " + principal.getUsername()));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        TokenDto tokenDto = verificationTokenRepository
                .findByUser(loginRequest.getUsername()).orElse(null);
        if (tokenDto == null)
            return createToken(loginRequest, false);
        Token verificationToken = handleUtilityService.mapTokenDTOList(tokenDto);
        try {
            if (!jwtProvider.validateToken(verificationToken.getTokenvalue())) {
                return createToken(loginRequest, true);
            }
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return null;
    }


    public AuthenticationResponse createToken(LoginRequest loginRequest, boolean tokenIsExist) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword(),
                        new ArrayList<>()
                ));
        System.out.println("authenticate " + authenticate);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        if (!tokenIsExist)
            verificationTokenRepository.save(new Token(token, loginRequest.getUsername()
                    , Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis())));

        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .username(loginRequest.getUsername())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .build();
    }

    public Boolean getAuthorization(String token) {
        String username = jwtProvider.getUsernameFromJwt(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        SimpleGrantedAuthority auth = authorities.stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("No user " +
                "Not Found : " + username));
        return auth.getAuthority().equals("admin");
    }

    @Transactional
    public void deleteToken(String token) {
        jwtProvider.deleteToken(token);
    }
}

/*








    public <T> T login(LoginRequest loginRequest) {
        TokenDto tokenDto = verificationTokenRepository
                .findByUser(loginRequest.getUsername()).orElse(null);
        if (tokenDto == null)
            return (T) createToken(loginRequest, false);
        Token verificationToken = handleUtilityService.mapTokenDTOList(tokenDto);
        try {
            if (!jwtProvider.validateToken(verificationToken.getTokenvalue())) {
                return (T) createToken(loginRequest, true);
            }
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
        return (T) new String("Already you are Login !!!");
    }



    public boolean expirationToken(String jwt) {
        try {
            parserBuilder()
                    .setSigningKey(getPublickey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
            System.out.println("Instant.now()  " + Instant.now());
            return false;

        } catch (ExpiredJwtException e) {
            System.out.println(" Token expired ");
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.error("ExpiredJwtException " + ex);
            return true;
        } catch (Exception e) {
            log.error("ExpiredJwtException " + e);
        }
        return false;
  }











//                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//                System.out.println("<============= Date Now =========== >" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:SS")));
                //
//                System.out.println("<=========== LocalDate.now().format(DateTimeFormatter.ISO_DATE =============>" + LocalDate.now().format(DateTimeFormatter.ISO_DATE_TIME));
//        verificationTokenRepository.save(new Token(token, loginRequest.getUsername(), "#'" + LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm:ss")) + "'#"));
//        verificationTokenRepository.save(new Token(token, loginRequest.getUsername(), "#'" + LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm:ss")) + "'#"));
//        String format = ;
//        System.out.println("formate date " + format);
//                LocalDate localDate = handleUtilityService.convertStrToDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault())));
//                verificationTokenRepository.save(new Token(token, loginRequest.getUsername(), Instant.parse(localDate.toString())));
//                Instant instant = Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis());
//                verificationTokenRepository.save(new Token(token, loginRequest.getUsername(), instant.format(DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault()))));
//                plusMillis(jwtProvider.getJwtExpirationInMillis()
//                verificationTokenRepository.save(new Token(token, loginRequest.getUsername(), Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis())));
//                String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault()));
//                LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(5);
//                localDateTime.toInstant(ZoneOffset.ofHours(8));

//                verificationTokenRepository.save(new Token(token, loginRequest.getUsername(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault()))));
//                LocalDateTime.now().plusMinutes(5).format(DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault()))
 */