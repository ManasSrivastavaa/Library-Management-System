package com.manas.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manas.Library.entity.Members;

public interface MemberRepository extends JpaRepository<Members,Long> {

}
