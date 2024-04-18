package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;

  public Member login(String loninId, String password) {
    return memberRepository.findByLoginId(loninId)
        .filter(m -> m.getPassword().equals(password))
        .orElse(null);
  }

}
