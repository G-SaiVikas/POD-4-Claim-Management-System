package com.policyservice.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policyservice.entity.Policy;
import com.policyservice.model.AddPolicy;
import com.policyservice.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;
	
	@Override
	public List<Policy> getAllPolicy(String userId) throws Exception  {
		List<Policy> allPolicies=this.policyRepository.findByUserId(userId);
		if(allPolicies.size()==0 || allPolicies==null) {
			throw new Exception("No Polices Found");
		}
		return allPolicies;
	}

	@Override
	public void addNewPolicy(String userId, AddPolicy addPolicy) {
		addPolicy.setDateOfPolicyPurchase(java.time.LocalDate.now());
		if(addPolicy.getPolicyDuration().length()==8) {
			addPolicy.setDateOfPolicyExpire(java.time.LocalDate.now().plusMonths(Long.parseLong(addPolicy.getPolicyDuration().substring(0, 1))));
		}else if(addPolicy.getPolicyDuration().length()==9) {
			addPolicy.setDateOfPolicyExpire(java.time.LocalDate.now().plusMonths(Long.parseLong(addPolicy.getPolicyDuration().substring(0, 2))));
		}
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Policy policy=modelMapper.map(addPolicy, Policy.class);
		policy.setPolicyId(UUID.randomUUID().toString());
		policy.setUserId(userId);
		policy.setStatus(false);
		this.policyRepository.save(policy);
		
	}


	@Override
	public void updatePolicyStatus(String policyId) {
		this.policyRepository.updatePolicy(policyId, true);
		
	}

}
