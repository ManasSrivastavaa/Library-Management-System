package com.manas.Library.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manas.Library.entity.Books;
import com.manas.Library.entity.Members;
import com.manas.Library.exception.BookLimitExceedException;
import com.manas.Library.exception.MemberNotFoundException;
import com.manas.Library.repository.MemberRepository;
import com.manas.Library.service.MemberService;
import com.manas.Library.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	private final TransactionService transactionService;

	
	@Override
	public List<Members> getAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Members getMemberById(Long id) {
		return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member not found with id : " + id));
	}

	@Override
	public Members saveMember(Members member) {
		return memberRepository.save(member);
	}

	@Override
	public Members updateMember(Long id, Members updatedMember) {
		var fetchedMember = getMemberById(id);
		fetchedMember.setAddress(updatedMember.getAddress());
		fetchedMember.setEmailId(updatedMember.getEmailId());
		fetchedMember.setNumber(updatedMember.getNumber());
		fetchedMember.setName(updatedMember.getName());
		return memberRepository.save(fetchedMember);
	}

	@Override
	public void deleteMember(Long id) {
	   getMemberById(id);
	   memberRepository.deleteById(id);	
	}

	@Override
	public Members assignBook(Long memberId, List<Books> books) {
		var fetchedMember = getMemberById(memberId);
		var memberBooks = fetchedMember.getRentedBooks();
		if(memberBooks.size() >= 3 || memberBooks.size() + books.size() > 3) {
			throw new BookLimitExceedException("Book limit of 3 exceeded");
		}
		memberBooks.addAll(books);
		fetchedMember.setRentedBooks(memberBooks);
		fetchedMember = memberRepository.save(fetchedMember);
		for(Books book : fetchedMember.getRentedBooks()) {
			transactionService.takeBook(fetchedMember, book);
		}
		return fetchedMember;
	}
	
	@Override
	public Members returnBooks(Long memberId, List<Books> books) {
		var fetchedMember = getMemberById(memberId);
		var memberBooks = fetchedMember.getRentedBooks();
		memberBooks.removeAll(books);
		fetchedMember.setRentedBooks(memberBooks);
		fetchedMember = memberRepository.save(fetchedMember);
		for(Books book : books) {
			transactionService.returnBook(fetchedMember, book);
		}
		return fetchedMember;
	}

	@Override
	public List<Books> getMemberBooks(Long userId) {
		var member = getMemberById(userId);
		return member.getRentedBooks();
	}

}
