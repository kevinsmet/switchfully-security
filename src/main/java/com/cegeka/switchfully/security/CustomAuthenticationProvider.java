package com.cegeka.switchfully.security;

import com.cegeka.switchfully.security.external.authentication.ExternalAuthenticaton;
import com.cegeka.switchfully.security.external.authentication.FakeAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private FakeAuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String name = authentication.getName();
       String password = authentication.getCredentials().toString();

       ExternalAuthenticaton user = authenticationService.getUser(name,password);

      if(user != null) {
          List<GrantedAuthority> roles = user.getRoles().stream()
                  .map(SimpleGrantedAuthority::new)
                  .collect(Collectors.toList());

          return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), roles);
      }

      else {
          throw new BadCredentialsException("User en/of paswoord niet gekend");
      }
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
