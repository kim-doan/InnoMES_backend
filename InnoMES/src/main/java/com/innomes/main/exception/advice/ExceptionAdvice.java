/*
 * 2019.09.24
 * 김도안
 * resources/i18n/exception_ko.yml에 있는 code와 메시지를 가져와 결과값으로 전송하는 부분
 * 
 */

package com.innomes.main.exception.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.innomes.main.exception.CAccountNotFoundException;
import com.innomes.main.exception.CAuthenticationEntryPointException;
import com.innomes.main.exception.CCompanySaveException;
import com.innomes.main.exception.CLoginFailException;
import com.innomes.main.exception.CManufactureProcessNotFoundException;
import com.innomes.main.exception.CManufactureProcessSaveException;
import com.innomes.main.exception.CMaterialSaveException;
import com.innomes.main.exception.COrderIdNotFoundException;
import com.innomes.main.exception.CPriceSaveException;
import com.innomes.main.exception.CProcessNotFoundException;
import com.innomes.main.exception.CProcessSaveException;
import com.innomes.main.exception.CProductSaveException;
import com.innomes.main.exception.CSalesOrderSaveException;
import com.innomes.main.exception.CSalesPlanNoNotFoundException;
import com.innomes.main.exception.CSalesPlanSaveException;
import com.innomes.main.exception.CSessionNotFoundException;
import com.innomes.main.exception.CSpareSaveException;
import com.innomes.main.exception.CStopCodeDuplicateException;
import com.innomes.main.exception.CStopCodeInfoSaveException;
import com.innomes.main.exception.CToolNotFoundException;
import com.innomes.main.exception.CToolSaveException;
import com.innomes.main.exception.CUserDuplicationException;
import com.innomes.main.exception.CUserNotFoundException;
import com.innomes.main.exception.CUserRegisterFailException;
import com.innomes.main.exception.CUserSaveException;
import com.innomes.main.exception.CValiedationItemCodeException;
import com.innomes.main.response.model.CommonResult;
import com.innomes.main.response.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

	private final ResponseService responseService;

	private final MessageSource messageSource;

	//	@Autowired
	//	private LabelInfoService labelInfoService;

	private String getMessage(String code) {
		return getMessage(code, null);
	}

	private String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}

	//	//알수없는 오류
	//	@ExceptionHandler(Exception.class)
	//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	//	protected CommonResult defaultException(HttpServletRequest request, Exception e) {
	//		//예외 처리 코드를 MessageSource에서 가져오도록 설정
	//		return responseService.getFailResult("E0001", labelInfoService.convertLabelName("E0001", "KR"));
	//	}

	//요청한 세션이 비어있을 경우
	@ExceptionHandler(CSessionNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult sessionNotFoundException(HttpServletRequest request, CSessionNotFoundException e) {
		return responseService.getFailResult("E0002", getMessage("sessionNotFoundException.msg"));
	}

	//존재하지 않는 회원
	@ExceptionHandler(CUserNotFoundException.class)
	//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		//예외 처리 코드를 MessageSource에서 가져오도록 설정
		return responseService.getFailResult("E0003", getMessage("userNotFoundException.msg"));
	}

	//토큰 만료 및 없을 경우 오류 처리
	@ExceptionHandler(CAuthenticationEntryPointException.class)
	public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
		return responseService.getFailResult("E0004", getMessage("authenticationEntryPointException.msg"));
	}

	//토큰 권한 오류
	@ExceptionHandler(AccessDeniedException.class)
	public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
		return responseService.getFailResult("E0005", getMessage("accessDeniedException.msg"));
	}

	//유저 아이디 중복
	@ExceptionHandler(CUserDuplicationException.class)
	public CommonResult userDuplicationException(HttpServletRequest request, CUserDuplicationException e) {
		return responseService.getFailResult("E0006", getMessage("userDuplicationException.msg"));
	}

	//회원가입 실패
	@ExceptionHandler(CUserRegisterFailException.class)
	public CommonResult userRegisterFailException(HttpServletRequest request, CUserRegisterFailException e) {
		return responseService.getFailResult("E0007", getMessage("userRegisterFailException.msg"));
	}

	//유저정보 저장실패
	@ExceptionHandler(CUserSaveException.class)
	public CommonResult userSaveException(HttpServletRequest request, CUserSaveException e) {
		return responseService.getFailResult("E0008", getMessage("userSaveException.msg"));
	}

	//제품정보 저장실패
	@ExceptionHandler(CProductSaveException.class)
	public CommonResult productSaveException(HttpServletRequest request, CProductSaveException e) {
		return responseService.getFailResult("E0009", getMessage("productSaveException.msg"));
	}

	//자재정보 저장실패
	@ExceptionHandler(CMaterialSaveException.class)
	public CommonResult materialSaveException(HttpServletRequest request, CMaterialSaveException e) {
		return responseService.getFailResult("E0010", getMessage("materialSaveException.msg"));
	}

	// 공구정보 저장실패
	@ExceptionHandler(CToolSaveException.class)
	public CommonResult toolSaveException(HttpServletRequest request, CToolSaveException e) {
		return responseService.getFailResult("E0011", getMessage("toolSaveException.msg"));
	}

	// 존재하지 않는 공구
	@ExceptionHandler(CToolNotFoundException.class)
	public CommonResult toolNotFoundException(HttpServletRequest request, CToolNotFoundException e) {
		return responseService.getFailResult("E0012", getMessage("toolNotFoundException.msg"));
	}

	// 예비품 저장실패
	@ExceptionHandler(CSpareSaveException.class)
	public CommonResult spareSaveException(HttpServletRequest request, CSpareSaveException e) {
		return responseService.getFailResult("E0013", getMessage("spareSaveException.msg"));
	}

	// 공정정보 조회 오류
	@ExceptionHandler(CProcessNotFoundException.class)
	public CommonResult processNotFoundException(HttpServletRequest request, CProcessNotFoundException e) {
		return responseService.getFailResult("E0014", getMessage("processNotFoundException.msg"));
	}

	// 공정정보 저장실패
	@ExceptionHandler(CProcessSaveException.class)
	public CommonResult processSaveException(HttpServletRequest request, CProcessSaveException e) {
		return responseService.getFailResult("E0015", getMessage("processSaveException.msg"));
	}

	//거래처 정보 저장실패
	@ExceptionHandler(CCompanySaveException.class)
	public CommonResult companySaveException(HttpServletRequest request, CCompanySaveException e) {
		return responseService.getFailResult("E0016", getMessage("companySaveException.msg"));
	}

	//판매계획번호 조회오류
	@ExceptionHandler(CSalesPlanNoNotFoundException.class)
	public CommonResult salesPlanNoNotFoundException(HttpServletRequest request, CSalesPlanNoNotFoundException e) {
		return responseService.getFailResult("E0017", getMessage("salesPlanNoNotFoundException.msg"));
	}

	//판매계획내역 저장실패
	@ExceptionHandler(CSalesPlanSaveException.class)
	public CommonResult salesPlanSaveException(HttpServletRequest request, CSalesPlanSaveException e) {
		return responseService.getFailResult("E0018", getMessage("salesPlanSaveException.msg"));
	}

	//수주번호 조회오류
	@ExceptionHandler(COrderIdNotFoundException.class)
	public CommonResult orderIdNotFoundException(HttpServletRequest request, COrderIdNotFoundException e) {
		return responseService.getFailResult("E0019", getMessage("orderIdNotFoundException.msg"));
	}

	//수주내역 저장실패
	@ExceptionHandler(CSalesOrderSaveException.class)
	public CommonResult salesOrderSaveException(HttpServletRequest request, CSalesOrderSaveException e) {
		return responseService.getFailResult("E0020", getMessage("salesOrderSaveException.msg"));
	}

	//계정 조회오류
	@ExceptionHandler(CAccountNotFoundException.class)
	public CommonResult accountNotFoundException(HttpServletRequest request, CAccountNotFoundException e) {
		return responseService.getFailResult("E0021", getMessage("accountNotFoundException.msg"));
	}

	//로그인 실패
	@ExceptionHandler(CLoginFailException.class)
	public CommonResult loginFailException(HttpServletRequest request, CLoginFailException e) {
		return responseService.getFailResult("E0022", getMessage("loginFailException.msg"));
	}

	//아이템코드 중복 에러
	@ExceptionHandler(CValiedationItemCodeException.class)
	public CommonResult valiedationItemCodeException(HttpServletRequest request, CValiedationItemCodeException e) {
		return responseService.getFailResult("E0023", getMessage("valiedationItemCodeException.msg"));
	}

	//단가정보 저장 실패
	@ExceptionHandler(CPriceSaveException.class)
	public CommonResult priceSaveException(HttpServletRequest request, CPriceSaveException e) {
		return responseService.getFailResult("E0024", getMessage("priceSaveException.msg"));
	}

	//비가동유형정보 저장 실패
	@ExceptionHandler(CStopCodeInfoSaveException.class)
	public CommonResult stopCodeInfoSaveException(HttpServletRequest request, CStopCodeInfoSaveException e) {
		return responseService.getFailResult("E0025", getMessage("stopCodeInfoSaveException.msg"));
	}

	//비가동유형정보 중복 에러
	@ExceptionHandler(CStopCodeDuplicateException.class)
	public CommonResult stopCodeDuplicateException(HttpServletRequest request, CStopCodeDuplicateException e) {
		return responseService.getFailResult("E0026", getMessage("stopCodeDuplicateException.msg"));
	}
	
	//제조공정정보 개정 실패
	@ExceptionHandler(CManufactureProcessSaveException.class)
	public CommonResult manufactureProcessSaveException(HttpServletRequest request, CManufactureProcessSaveException e) {
		return responseService.getFailResult("E0027", getMessage("manufactureProcessSaveException.msg"));
	}
	
	//제조공정정보 존재 하지 않음
	@ExceptionHandler(CManufactureProcessNotFoundException.class)
	public CommonResult manufactureProcessNotFoundException(HttpServletRequest request, CManufactureProcessNotFoundException e) {
		return responseService.getFailResult("E0028", getMessage("manufactureProcessNotFoundException.msg"));
	}
}