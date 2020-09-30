package au.com.carsguide.memberservice.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.carsguide.memberservice.eventsourcing.MemberEvent;
import au.com.carsguide.memberservice.eventsourcing.MemberEventService;
import au.com.carsguide.memberservice.eventsourcing.MemberEvent.EventStatus;
import au.com.carsguide.memberservice.models.Member;
import au.com.carsguide.memberservice.models.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberEventService memberEventService;
	
	/**
	 * Get all members
	 * 
	 * @return Collection of all members
	 */
	public Iterable<Member> getAllMembers() {
		logger.info("Retrieving all members");
		return memberRepository.findAll();
	}

	/**
	 * Add new Member
	 * 
	 * @param member
	 * @return String response message
	 */
	public String addMember(@Valid Member member) {
		logger.info("Saving member with email: " + member.getEmail());
		memberRepository.save(member);
		memberEventService.addEvent(new MemberEvent(member, EventStatus.CREATED));
		return "Member created successfully";
	}

	/**
	 * Update a Member
	 * 
	 * @param id
	 * @param member
	 * @return String response message
	 */
	public String updateMember(Long id, Member member) {
		logger.info("Updating member with id: " + member.getId());
		member.setId(id);
		memberRepository.save(member);
		memberEventService.addEvent(new MemberEvent(member, EventStatus.UPDATED));
		return "Member updated successfully";
	}

	/**
	 * Get single member by id
	 * 
	 * @param id
	 * @return Member identified by id
	 */
	public Optional<Member> getMember(Long id) {
		logger.info("Retrieving member with id: " + id);
		return memberRepository.findById(id);
	}

	/**
	 * Delete a Member
	 * 
	 * @param id
	 * @return String response message
	 */
	public String deleteMember(Long id) {
		logger.info("Deleting member with id: " + id);
		Member member = memberRepository.findById(id).orElseThrow();
		memberRepository.deleteById(id);
		memberEventService.addEvent(new MemberEvent(member, EventStatus.DELETED));
		return "Member deleted successfully";
	}

	/**
	 * Delete aLl Members
	 * 
	 * @return String response message
	 */
	public String deleteAllMembers() {
		logger.info("Deleting all members");
		memberRepository.deleteAll();
		return "Members deleted successfully";
	}
}
