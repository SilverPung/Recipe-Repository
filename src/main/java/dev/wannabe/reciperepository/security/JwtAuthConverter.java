package dev.wannabe.reciperepository.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;


import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Slf4j
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {


    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.converter.preffered-atribute}")
    private String principleAttribute;

    @Value("${jwt.auth.converter.client_id}")
    private String clientId;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
            jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
        )
                .collect(Collectors.toSet());


        log.info("Authorities: {}", authorities);
        return new JwtAuthenticationToken(
                jwt,
                authorities,
                getPrincipalName(jwt)

        );
    }

    private String getPrincipalName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if(principleAttribute != null)
            claimName = principleAttribute;

        return jwt.getClaimAsString(claimName);
    }


    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> clientAccess;
        Collection<String> roles;

        if(jwt.getClaim("resource_access") == null) {
            return Set.of();
        }
        resourceAccess = jwt.getClaim("resource_access");

        if(resourceAccess.get(clientId) == null) {
            return Set.of();
        }
        clientAccess = (Map<String, Object>) resourceAccess.get(clientId);

        if(clientAccess.get("roles") == null) {
            return Set.of();
        }
        roles = (Collection<String>) clientAccess.get("roles");
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());

    }


}
