package com.userservice.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.userservice.model.AddClaim;
import com.userservice.model.AddPolicy;
import com.userservice.model.GeneralResponseModel;
import com.userservice.model.UnAuthorizedAccess;
import com.userservice.model.User;
import com.userservice.model.UserResponseModel;
import com.userservice.services.UserService;
import com.userservice.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	/*Injecting the UserService class object here*/
	@Autowired
	private UserService userService;
	
	/* This method allows a new user to signup.If the given credentials is already signed up it will show that user already exists 
	 * The Api end point is -> http://localhost:8717/user-ms/users
	 * */
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto=modelMapper.map(user, UserDto.class);
		userDto.setRole("ROLE_USER");
		ResponseEntity<?> responseEntity=this.userService.createUser(userDto);
		return responseEntity;
	}
	/* This method fetches all the added policy by the user in a list.If a user does not have any added policy it will show 
	 * no added policy by the user
	 * The Api end point is -> http://localhost:8717/user-ms/users/allPolicies/{userId}
	 * */
	@GetMapping("/allPolicies/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") String userId,@RequestHeader("Authorization") String authHeader) {
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		UserResponseModel userResponseModel=new UserResponseModel();
		if(userDto==null || userDto.getAllPolicies().size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new GeneralResponseModel("No Policy Found for the User ! Add new Policy to get Started"));
		}
		userResponseModel.setAllPolicies(userDto.getAllPolicies());
		return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
	}
	/* This method adds a policy for a particular user.
	 * The Api end point is -> http://localhost:8717/user-ms/users/addPolicy/{userId}
	 * */
	@PostMapping("/addPolicy/{userId}")
	public ResponseEntity<?> addPolicy(@PathVariable("userId") String userId,@RequestBody AddPolicy policy,@RequestHeader("Authorization") String authHeader) {
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		this.userService.addPolicy(userId, policy);
		return ResponseEntity.status(HttpStatus.OK).body(new GeneralResponseModel("Added"));
	}
	/* This method adds a claim for a particular user & a particular policy.
	 * The Api end point is -> http://localhost:8717/user-ms/users/addClaim/{userId}/{policyId}
	 * */
	@PostMapping("/addClaim/{userId}/{policyId}")
	public ResponseEntity<?> addClaim(@PathVariable("userId") String userId,@PathVariable("policyId") String policyId,@RequestBody AddClaim claim,@RequestHeader("Authorization") String authHeader) {
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		this.userService.addClaim(policyId,userId, claim);
		this.userService.updatePolicyStatus(policyId);
		return ResponseEntity.status(HttpStatus.OK).body(new GeneralResponseModel("Claim Added Successfully"));
	}
	/* This method fetches all claims for a particular user.
	 * The Api end point is -> http://localhost:8717/user-ms/users/getAllClaims/{userId}
	 * */
	@GetMapping("/getAllClaims/{userId}")
	public ResponseEntity<?> getAllClaims(@PathVariable("userId") String userId,@RequestHeader("Authorization") String authHeader){
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		return this.userService.getAllClaims(userId);
	}
	/* This method fetches claim status for a particular policy added by a specific user.
	 * The Api end point is -> http://localhost:8717/user-ms/users/claimStatus/{userId}/{policyId}
	 * */
	@GetMapping("/claimStatus/{userId}/{policyId}")
	public ResponseEntity<?> getClaimStatus(@PathVariable("userId") String userId,@PathVariable("policyId") String policyId,@RequestHeader("Authorization") String authHeader) {
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		return this.userService.getClaimStatus(policyId,userId);
	}
	/* This method logs out the user
	 * The Api end point is -> http://localhost:8717/user-ms/users/logout/{userId}
	 * */
	@GetMapping("/logout/{userId}")
	public void logout(@PathVariable("userId") String userId) {
		this.userService.updateJwtToken(userId,"");
	}
}

