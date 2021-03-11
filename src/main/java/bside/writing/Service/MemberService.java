package bside.writing.Service;

import bside.writing.domain.member.Member;
import bside.writing.domain.member.NewMemberRepository;
import bside.writing.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final NewMemberRepository newMemberRepository;

    @Transactional
    public MemberDto join(MemberDto memberDto) {
        Member entity = memberDto.toEntity();
        newMemberRepository.save(entity);
        return new MemberDto(entity);
    }

    @Transactional
    public MemberDto findByEmail(String email){
        Member entity = newMemberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않거나 중복 : " + email));
        return new MemberDto(entity);
    }

    @Transactional
    public MemberDto update(String email, String newNickName, String newPictureURL){
        Member member = newMemberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않거나 중복 : " + email));
        member.update(newNickName, newPictureURL);
        return new MemberDto(member);
    }

    @Transactional
    public boolean has(String email) {
        Optional<Member> member = newMemberRepository.findByEmail(email);
        if(member.isPresent()) return true;
        return false;
    }
}

