package com.manas.Library.service;

import java.util.List;

import com.manas.Library.entity.Books;
import com.manas.Library.entity.Members;

public interface MemberService {

	List<Members> getAllMembers();

	Members getMemberById(Long id);

	Members saveMember(Members member);

	Members updateMember(Long id, Members updatedMember);

	void deleteMember(Long id);
	
	Members assignBook(Long memberId, List<Books> book);

	Members returnBooks(Long memberId, List<Books> books);
	
	List<Books> getMemberBooks(Long userId);

}
