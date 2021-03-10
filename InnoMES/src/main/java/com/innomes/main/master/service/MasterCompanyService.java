package com.innomes.main.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.common.reflect.TypeToken;
import com.innomes.main.master.dto.MasterCompanyDTO;
import com.innomes.main.master.dto.MasterProductDTO;
import com.innomes.main.master.model.MST111;
import com.innomes.main.master.model.MST150;
import com.innomes.main.master.param.MasterCompanyParam;
import com.innomes.main.repository.MST150Repository;

@Service
@Transactional
public class MasterCompanyService {
	@Autowired
	private MST150Repository mst150Repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//거래처 조회
	public Page<MasterCompanyDTO> getCompInfo(MasterCompanyParam masterCompanyParam, Pageable pageable){
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Page<MST150> output = mst150Repository.findAllLike(masterCompanyParam, pageable);
		return new PageImpl<>(modelMapper.map(output.getContent(), new TypeToken<List<MasterCompanyDTO>>(){}.getType()),pageable, output.getTotalElements());
	}
	
	public List<MasterCompanyDTO> findAll() {
		List<MST150> content = mst150Repository.findAll();
		
		List<MasterCompanyDTO> dtoList = new ArrayList<MasterCompanyDTO>();
		
		for(int i=0;i<content.size();i++) {
			MasterCompanyDTO dto = MasterCompanyDTO.builder()
					.compId(content.get(i).getCompId())
					.compName(content.get(i).getCompName())
					.companyNo(content.get(i).getCompanyNo())
					.saleYN(content.get(i).getSaleYN())
					.purchaseYN(content.get(i).getPurchaseYN())
					.outsourcingYN(content.get(i).getOutsourcingYN())
					.domesticYN(content.get(i).getDomesticYN())
					.destributeType(content.get(i).getDestributeType())
					.business(content.get(i).getBusiness())
					.industry(content.get(i).getIndustry())
					.representative(content.get(i).getRepresentative())
					.compPhone(content.get(i).getCompPhone())
					.compFax(content.get(i).getCompFax())
					.compEmail(content.get(i).getCompEmail())
					.postNo(content.get(i).getPostNo())
					.address(content.get(i).getAddress())
					.homepage(content.get(i).getHomepage())
					.mainCharge(content.get(i).getMainCharge())
					.chargePhone(content.get(i).getChargePhone())
					.chargeCp(content.get(i).getChargeCp())
					.chargeFax(content.get(i).getChargeFax())
					.chargeEmail(content.get(i).getChargeEmail())
					.description(content.get(i).getDescription())
					.createUser(content.get(i).getCreateUser())
					.createTime(content.get(i).getCreateTime())
					.updateUser(content.get(i).getUpdateUser())
					.updateTime(content.get(i).getUpdateTime())
					.used(content.get(i).getUsed())
					.build();
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	
	public boolean setCompInfo(List<MST150> compParam) {
		boolean success = true;
		
		try {
			for (MST150 mst150 : compParam) {
				mst150.setCreateTime(new Date());
				mst150.setUpdateTime(new Date());
			}
			
			mst150Repository.saveAll(compParam);
			mst150Repository.flush();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return success;
	}
}
