package com.userservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userservice.data.ClaimServiceClient;
import com.userservice.data.Policy;
import com.userservice.data.PolicyServiceClient;
import com.userservice.data.UserEntity;
import com.userservice.model.AddClaim;
import com.userservice.model.AddPolicy;
import com.userservice.model.GeneralResponseModel;
import com.userservice.repository.UserRepository;
import com.userservice.shared.UserDto;


@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PolicyServiceClient policyServiceClient;
	
	@Autowired
	private ClaimServiceClient claimServiceClient;
	
	@Override
	public ResponseEntity<?> createUser(UserDto userDetails) {
		if(this.userRepository.findByEmail(userDetails.getEmail())!=null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponseModel("User Already Exists"));
		}
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity=modelMapper.map(userDetails, UserEntity.class);
		this.userRepository.save(userEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(new GeneralResponseModel("Signed Up Successfully"));
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity=this.userRepository.findByEmail(username);
		if(userEntity==null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
	}


	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity=this.userRepository.findByEmail(email);
		if(userEntity==null) {
			throw new UsernameNotFoundException(email);
		}
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity=this.userRepository.findByUserId(userId);
		if(userEntity==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		UserDto userDto=new ModelMapper().map(userEntity, UserDto.class);
		List<Policy> policyList=new ArrayList<>();
		try {
			policyList=policyServiceClient.getPolicies(userId);
		}catch(Exception e) {
			policyList=new ArrayList<>();
			userDto.setAllPolicies(policyList);
			return userDto;
		}
		userDto.setAllPolicies(policyList);
		return userDto;
	}


	@Override
	public void addPolicy(String userId, AddPolicy addPolicy) {
		 this.policyServiceClient.addPolicy(userId,addPolicy);
		
	}


	@Override
	public void updateJwtToken(String userId,String jwtToken) {
		this.userRepository.updateJwtByUserId(jwtToken, userId);
		
	}


	@Override
	public void addClaim(String policyId,String userId ,AddClaim claim) {
		this.claimServiceClient.addClaim(policyId, userId,claim);
		
	}


	@Override
	public ResponseEntity<?> getClaimStatus(String policyId,String userId) {
		return	this.claimServiceClient.getClaimStatus(policyId,userId);
	}


	@Override
	public ResponseEntity<?> getAllClaims(String userId) {
		return this.claimServiceClient.getAllClaims(userId);
	}


	@Override
	public void updatePolicyStatus(String policyId) {
		this.policyServiceClient.updatePolicyStatus(policyId);
		
	}
	


}
