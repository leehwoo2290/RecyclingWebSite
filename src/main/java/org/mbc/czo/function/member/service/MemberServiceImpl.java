package org.mbc.czo.function.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.czo.function.member.constant.Role;
import org.mbc.czo.function.member.domain.Member;
import org.mbc.czo.function.member.dto.MemberFindIDDTO;
import org.mbc.czo.function.member.dto.MemberJoinDTO;
import org.mbc.czo.function.member.repository.MemberJpaRepository;
import org.mbc.czo.function.member.security.dto.MemberSecurityDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor // final붙은 필드를 생성자로
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;  // 엔티티를 dto변환
    private final MemberJpaRepository memberJpaRepository;  // member db 처리용
    private final PasswordEncoder passwordEncoder;    // 패스워드 암호화

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws M_idExistException {

        String m_id = memberJoinDTO.getM_id();
        boolean exist = memberJpaRepository.existsById(m_id); // 기존에 id 있는지 boolean return

        if(exist) {
            throw new M_idExistException(); // 중복id 처리용 예외처리 발생
        }

        // 진짜 회원가입처리
        Member member = modelMapper.map(memberJoinDTO, Member.class);
        // 엔티티                              dto

       // member.setM_password(memberJoinDTO.getM_password());
        member.setM_password(passwordEncoder.encode(memberJoinDTO.getM_password()));
        member.addRole(Role.USER);

        log.info("=============================");
        log.info(memberJoinDTO);
        log.info(member.toString());

        memberJpaRepository.save(member);
    }

    @Override
    public void findID(MemberFindIDDTO memberFindIDDTO) throws M_idExistException {

        String m_name = memberFindIDDTO.getM_name();
        String m_phoneNumber = memberFindIDDTO.getM_phoneNumber();
        Optional<Member> result = memberJpaRepository.checkExistIDFromNameAndPhoneNumber(m_name,m_phoneNumber);

        if(result.isEmpty()){
            // 해당하는 정보가 db에 없으면
            throw new M_idExistException();
        }

        Member member = result.get(); // 해당하는 member가 있으면 넣음

        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                member.getM_id(),
                member.getM_password(),
                member.getM_email(),
                member.isM_isActivate(),
                false,  //boolean social
                member.getM_roleSet().stream().map(memberRole ->
                                new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                        // ROLE_USER, ROLE_ADMIN
                        .collect(Collectors.toList() // ROLE_USER, ROLE_ADMIN
                        )
        );
        log.info("CustomUserDetailsService.loadUserByUsername 메서드 실행.....");
        log.info("memberSecurityDTO :" + memberSecurityDTO);

        //return memberSecurityDTO;
    }


}
