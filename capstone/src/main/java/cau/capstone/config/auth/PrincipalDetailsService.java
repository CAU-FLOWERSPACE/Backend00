package cau.capstone.config.auth;

import cau.capstone.domain.User;
import cau.capstone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    log.info("[request login] " + email);
    User userEntity = userRepository.findByEmail(email);

    if (userEntity != null) {
      return new PrincipalDetails(userEntity);
    }
    throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
  }
}
