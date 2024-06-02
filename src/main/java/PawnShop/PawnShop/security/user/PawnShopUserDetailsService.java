/*
package PawnShop.PawnShop.security.user;


import PawnShop.PawnShop.model.User;
import PawnShop.PawnShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

*/
/**
 * @author Simpson Alfred
 *//*

@Service
@RequiredArgsConstructor
public class PawnShopUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return PawnShopUserDetails.buildUserDetails(user);
    }
}
*/
