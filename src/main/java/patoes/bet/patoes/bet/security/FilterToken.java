package patoes.bet.patoes.bet.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import patoes.bet.patoes.bet.repository.UsuarioRepository;
import patoes.bet.patoes.bet.service.TokenService;

import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        var authorization = request.getHeader("Authorization");

        try {
            if (authorization != null) {
                token = authorization.replace("Bearer ", "");

                var subject = tokenService.getSubject(token);

                var usuario = usuarioRepository.findByEmail(subject).get();

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (TokenExpiredException e){
        }

        filterChain.doFilter(request, response);
    }
}
