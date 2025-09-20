package com.manas.Library.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manas.Library.entity.Books;
import com.manas.Library.entity.Members;
import com.manas.Library.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Books>> getMyBooks(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberBooks(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Members>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }
    
    @PostMapping
    public ResponseEntity<Members> createMember(@Valid @RequestBody Members member) {
        Members savedMember = memberService.saveMember(member);
        URI location = URI.create("/api/member/" + savedMember.getId());
        return ResponseEntity.created(location).body(savedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Members> updateMember(@PathVariable Long id, @Valid @RequestBody Members member) {
        Members updatedMember = memberService.updateMember(id, member);
        return ResponseEntity.ok(updatedMember);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Members> getById(@PathVariable Long id) {
        Members updatedMember = memberService.getMemberById(id);
        return ResponseEntity.ok(updatedMember);
    }
}

