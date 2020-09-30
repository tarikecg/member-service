package au.com.carsguide.memberservice.controllers;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import au.com.carsguide.memberservice.models.Member;
import au.com.carsguide.memberservice.services.MemberService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Member Controller
 * 
 * Operations for maintaining member details
 * 
 * Note: the endpoint design approach adopted is resource-based where the resource here is "member"
 * 
 * @author Tarik Helmey <thelmey@gmail.com>
 * 
 */
@Controller
@RequestMapping(path="/members")
@Validated
@OpenAPIDefinition(
	externalDocs = @ExternalDocumentation(
		description= "Carsguide Engineering",
		url="https://engineering.carsguide.com.au/"),
	info = @Info(
		title="Member Service API", 
		version = "1.0",
		description = "Operations for maintaining member details.",
		termsOfService = "http://carsguide.com.au/terms", 
		contact = @Contact(
			name = "Carsguide API Support",
			url = "http://carsguide.com.au/contact",
			email = "techsupport@carsguide.com.au")))
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * Get all Members
	 * 
	 * @return Response body containing a JSON array of Members
	 */
	@GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "List all members")
	@ApiResponse(responseCode = "200", description = "Success")
	@ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Member.class))))
	public @ResponseBody Iterable<Member> getAllMembers() {
		logger.info("Listing all members");
		return memberService.getAllMembers();
	}
	
	/**
	 * Add new Member
	 * 
	 * @param member
	 * @param bindingResult
	 * @return String response message
	 */
	@PostMapping(path="/")
	@Operation(summary = "Add a member")
	@ApiResponse(responseCode = "201", description = "Success", content = @Content(schema = @Schema(type = "string", implementation = Member.class)))
	@ApiResponse(responseCode = "400", description = "Validation failed")
	@ApiResponse(responseCode = "409", description = "Member already exists")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String addNewMember (@Valid @RequestBody Member member) {
		logger.info("Adding new member: " + member);
		return memberService.addMember(member);
	}
	
	/**
	 * Update/edit a Member
	 * 
	 * @param id
	 * @param member
	 * @return String response message
	 */
	@PutMapping(path="/{id}")
	@Operation(summary = "Update a member")
	@Parameter(name = "id", description = "Member Id", required = true)
	@ApiResponse(responseCode = "200", description = "Success")
	@ApiResponse(responseCode = "400", description = "Invalid Member Id supplied")
	@ApiResponse(responseCode = "409", description = "Another Member with this email address already exists")
	public @ResponseBody String updateMember(@PathVariable(name = "id") Long id, @Valid @RequestBody Member member) {
		logger.info("Updating member: " + member);
		return memberService.updateMember(id, member);
	}
	
	/**
	 * Get/view single Member by Id
	 * 
	 * @param id
	 * @return Response body containing a JSON Member object
	 */
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "View a member")
	@Parameter(name = "id", description = "Member Id", required = true)
	@ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Member.class)))
	@ApiResponse(responseCode = "400", description = "Invalid Member Id supplied", content = @Content(schema = @Schema(implementation = Void.class)))
	public @ResponseBody Optional<Member> getMemberById(@PathVariable(name = "id") @Positive Long id) {
		logger.info("View member: " + id);
		return memberService.getMember(id);
	}
	
	/**
	 * Delete a Member
	 * 
	 * @param id
	 * @return String response message
	 */
	@DeleteMapping(path="/{id}")
	@Operation(summary = "Delete a member")
	@Parameter(name = "id", description = "Member Id", required = true)
	@ApiResponse(responseCode = "200", description = "Success")
	@ApiResponse(responseCode = "400", description = "Invalid Member Id supplied")
	public @ResponseBody String deleteMember(@PathVariable(name = "id") Long id) {
		logger.info("Removing member: " + id);
		return memberService.deleteMember(id);
	}
}
