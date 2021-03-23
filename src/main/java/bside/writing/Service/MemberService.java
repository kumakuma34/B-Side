package bside.writing.Service;

import bside.writing.domain.member.Member;
import bside.writing.Repository.MemberRepository;
import bside.writing.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto joinOrLogin(MemberDto memberDto){
        boolean firstJoin = false;
        if(has(memberDto.getEmail())){
            firstJoin = true;
            join(memberDto);
        }
        return findByEmail(memberDto.getEmail());
    }



    @Transactional
    public MemberDto join(MemberDto memberDto) {
        Member entity = memberDto.toEntity();
        memberRepository.save(entity);
        return new MemberDto(entity);
    }

    public MemberDto findById(Long id){
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());
        return new MemberDto(entity);
    }

    @Transactional
    public MemberDto findByEmail(String email){
        Member entity = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException());
        return new MemberDto(entity);
    }

    @Transactional
    public MemberDto update(Long memberId, String newNickName, String newPictureURL){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());
        member.update(newNickName, newPictureURL);
        return new MemberDto(member);
    }

    @Transactional
    public boolean has(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) return true;
        return false;
    }


}

