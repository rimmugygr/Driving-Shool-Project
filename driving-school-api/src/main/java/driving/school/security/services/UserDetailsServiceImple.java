package driving.school.security.services;

import driving.school.model.user.User;
import driving.school.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserDetailsServiceImple implements UserDetailsService {
    private final UserRepo userRep;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRep.getOneByUsername(s)
                .map(BridgeUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(s));
    }



    static class BridgeUser extends User implements UserDetails {

        public BridgeUser(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.getRoles().stream()
                    .map(a -> new SimpleGrantedAuthority(a.getName().name()))
                    .collect(Collectors.toList());
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }



}

