package com.innomes.main.master.dto;

import java.util.Date;

import com.innomes.main.master.dto.MasterProductDTO.MasterProductDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MasterToolDTO {
	/* MST 110*/
	private String itemId; // 품목ID(PK)
	
	private String itemCode; // 품목코드
	
	private String itemName; // 품목명
	
	private String itemType; // 품목유형
	
	private double lotSize; // 로트사이즈
	
	private String lotUnit; // 로트단위
	
	private double safetyQnt; // 안전재고기준
	
	private String safetyUnit; // 안전재고단위
	
	private String locCode; // 적재위치
	
	private Integer invType; // 재고관리구분
	
	private String description; // 비고
	
	/* MST113 */
	private String toolId; // 공구ID
	
	private String toolType; // 공구유형
	
	private String toolCtg; // 공구카테고리
	
	private String toolGroup; // 공구그룹
	
	private String toolProc; // 공구단수
	
	private double toolLifeCnt; // 공구기준수명
	
	private double toolWarningRate; // 공구경고비율
	
	private double toolChkCycle; // 점검주기
	
	private double toolRecycleCnt; // 가공기준횟수
	
	private String repItemId; // 대표품번
	
	private String prdtionProc; // 생산공정
	
	private int lotYN; // 로트관리유무
}
