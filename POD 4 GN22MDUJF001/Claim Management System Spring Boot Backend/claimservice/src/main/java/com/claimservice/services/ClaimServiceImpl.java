package com.claimservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.claimservice.entity.Claim;
import com.claimservice.model.AddClaim;
import com.claimservice.model.AdminModel;
import com.claimservice.model.ClaimNotFound;
import com.claimservice.model.ClaimResponseModel;
import com.claimservice.model.ClaimStatusModel;
import com.claimservice.model.GeneralResponseModel;
import com.claimservice.repository.ClaimRepository;


@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRepository claimRepository;
	@Override
	public void addClaim(String policyId,String userId ,AddClaim addClaim) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Claim claim=modelMapper.map(addClaim, Claim.class);
		claim.setPolicyId(policyId);
		claim.setClaimId(UUID.randomUUID().toString());
		claim.setUserId(userId);
		claim.setClaimStatus("Pending");
		this.claimRepository.save(claim);
	}

	@Override
	public ResponseEntity<?> getClaimStatus(String policyId,String userId) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ClaimStatusModel claimStatusModel=new ClaimStatusModel();
		try {
			Claim claim=this.claimRepository.findByPolicyId(policyId);
			claimStatusModel=modelMapper.map(claim, ClaimStatusModel.class);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClaimNotFound(policyId +" does not exist!! Please check the Policy Id"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(claimStatusModel);
		
	}

	@Override
	public ResponseEntity<?> getAllClaims(String userId) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Claim> allClaims=this.claimRepository.findByUserId(userId);
		if(allClaims==null || allClaims.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ClaimNotFound(userId+" does not have any Claims"));
		}
		List<ClaimResponseModel> claims=new ArrayList<>();
		for(int i=0;i<allClaims.size();i++) {
			ClaimResponseModel claimResponseModel=modelMapper.map(allClaims.get(i), ClaimResponseModel.class);
			claims.add(claimResponseModel);
		}
		return ResponseEntity.status(HttpStatus.OK).body(claims);
	}

	@Override
	public ResponseEntity<?> pendingClaims() {
		List<Claim> claims= this.claimRepository.findByClaimStatus("pending");
		if(claims==null || claims.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ClaimNotFound("No Pending Claims Found"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(claims);
	}

	@Override
	public ResponseEntity<?> acceptedClaims() {
		List<Claim> claims= this.claimRepository.findByClaimStatus("accepted");
		if(claims==null || claims.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ClaimNotFound("No Accepted Claims Found"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(claims);
	}

	@Override
	public ResponseEntity<?> rejectedClaims() {
		List<Claim> claims= this.claimRepository.findByClaimStatus("rejected");
		if(claims==null || claims.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ClaimNotFound("No Rejected Claims Found"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(claims);
	}

	@Override
	public ResponseEntity<?> acceptClaim(String claimId,AdminModel adminModel) {
		System.out.println(claimId+" "+adminModel.getAdminClaimDescription());
		try {
			this.claimRepository.updateClaimStatus(claimId, adminModel.getClaimStatus());
			this.claimRepository.updateAdminClaimDescription(claimId,adminModel.getAdminClaimDescription());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponseModel("Cannot Update the Claim Status"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new GeneralResponseModel("Status Updated to Accepted for Claim Id "+claimId+" Successfully"));
		
	}
	@Override
	public ResponseEntity<?> rejectClaim(String claimId,AdminModel adminModel) {
		try {
			this.claimRepository.updateClaimStatus(claimId, adminModel.getClaimStatus());
			this.claimRepository.updateAdminClaimDescription(claimId,adminModel.getAdminClaimDescription());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponseModel("Cannot Update the Claim Status"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new GeneralResponseModel("Status Updated to Rejected for Claim Id "+claimId+" Successfully"));
		
	}

}
