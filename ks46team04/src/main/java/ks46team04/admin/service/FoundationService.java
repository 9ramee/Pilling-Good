package ks46team04.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks46team04.admin.dto.Foundation;
import ks46team04.admin.dto.FoundationRequest;
import ks46team04.admin.mapper.CommonMapper;
import ks46team04.admin.mapper.FoundationMapper;

@Service
@Transactional
public class FoundationService {

	
	private static final Logger log = LoggerFactory.getLogger(FoundationService.class);

	private final FoundationMapper foundationMapper;
	private final CommonMapper commonMapper;
	
	public FoundationService(FoundationMapper foundationMapper, CommonMapper commonMapper) {
		this.foundationMapper = foundationMapper;
		this.commonMapper = commonMapper;
	}
	
	/**
	 * 재단 요청사항 수정
	 * @param foundationRequest
	 */
	public void modifyFoundationRequest(FoundationRequest foundationRequest) {
		// 재단명으로 재단 코드 조회 및 설정
		String foundationCode = foundationMapper.getFoundationCodeByName(foundationRequest.getFoundationName());
		foundationRequest.setFoundationCode(foundationCode);
		log.info("수정 후 foundationRequest: {}", foundationRequest);
		
		foundationMapper.modifyFoundationRequest(foundationRequest);
	}
	
	/**
	 * 특정 재단 요청사항 조회
	 * @param foundationRequestCode
	 * @return
	 */
	public FoundationRequest getFoundationRequestInfoByCode(String foundationRequestCode) {
		FoundationRequest foundationRequestInfo = foundationMapper.getFoundationRequestInfoByCode(foundationRequestCode);
		return foundationRequestInfo;
	}
	
	/**
	 * 재단 요청사항 등록
	 * @param foundationRequest
	 * @return
	 */
	public int addFoundationRequest(FoundationRequest foundationRequest) {
		
		// 재단요청사항코드 - 공통 mapper를 사용하여 foundationRequestCode 생성 및 설정
		String foundationRequestCode = commonMapper.getPrimaryKeyVerTwo("foundation_request"
																, "foundation_request_code"
																, "foundation_request");
		foundationRequest.setFoundationRequestCode(foundationRequestCode);
		
		// 재단명으로 재단 코드 조회 및 설정
		String foundationCode = foundationMapper.getFoundationCodeByName(foundationRequest.getFoundationName());
		foundationRequest.setFoundationCode(foundationCode);
		
		log.info("등록된 foundationRequest: {}", foundationRequest);
		
		int result = foundationMapper.addFoundationRequest(foundationRequest);
		return result;
	}
	
	/**
	 * 재단 요청사항 삭제
	 * @param foundationRequestCode
	 * @return
	 */
	public boolean removeFoundationRequest(String foundationRequestCode) {
		boolean isRemove = foundationMapper.removeFoundationRequestCheck(foundationRequestCode);
		if(isRemove){
			foundationMapper.removeFoundationRequest(foundationRequestCode);
			return true;
		}
		return false;
	}
	
	/**
	 * 재단 요청사항 검색 결과 조회
	 * @param paramMap
	 * @return
	 */
	public List<FoundationRequest> getFoundationRequestlistBySearch(String inputSearchKey
																	,String inputSearchValue
																	,String contentKey
																	,String contentValue
																	,String progressKey
																	,String progressValue
																	,String dateSearchKey
																	,String startDate
																	,String endDate){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		if(inputSearchKey != null && inputSearchValue != null) {
			switch (inputSearchKey) {
			case "foundationName":
				inputSearchKey = "foundation_name";
				break;
			case "requestGoodsName":
				inputSearchKey = "request_goods_name";					
				break;
			}
			searchMap.put("inputSearchKey", inputSearchKey);
			searchMap.put("inputSearchValue", inputSearchValue);
		}
		
		if(contentKey != null && contentValue != null) {
			contentKey = "content_category";					
			searchMap.put("contentKey", contentKey);
			
			boolean isAll = contentValue.equals("전체");
			if(isAll) contentValue = "";
			searchMap.put("contentValue", contentValue);
		}
		
		if(progressKey != null && progressValue != null) {
			progressKey = "request_progress_status";					
			searchMap.put("progressKey", progressKey);
			
			boolean isAll = progressValue.equals("전체");
			if(isAll) progressValue = "";
			searchMap.put("progressValue", progressValue);
		}
		
		if(dateSearchKey != null && startDate != null && endDate != null) {
			searchMap.put("dateSearchKey", dateSearchKey);
			searchMap.put("startDate", startDate);
			searchMap.put("endDate", endDate);
		}
		
		log.info("searchMap: {}", searchMap);
		
		List<FoundationRequest> foundationRequestList = foundationMapper.getFoundationRequestListBySearch(searchMap);
		return foundationRequestList;
	}
	
	/**
	 * 재단 요청사항 조회
	 * @return
	 */
	public List<FoundationRequest> getFoundationRequestlist(){
		List<FoundationRequest> foundationRequestList = foundationMapper.getFoundationRequestList();
		return foundationRequestList;
	}
	
	/**
	 * 재단 수정
	 * @param foundation
	 */
	public void modifyFoundation(Foundation foundation) {
		foundationMapper.modifyFoundation(foundation);
	}
	
	/**
	 * 특정 재단 정보 조회
	 * @param foundationCode
	 * @return
	 */
	public Foundation getFoundationInfoByCode(String foundationCode) {
		Foundation foundationInfo = foundationMapper.getFoundationByCode(foundationCode);
		return foundationInfo;
				
	}
	
	/**
	 * 재단 등록
	 * @param foundation
	 * @return
	 */
	public int addFoundation(Foundation foundation) {
		
		int result = foundationMapper.addFoundation(foundation);
		return result;
	}
	
	/**
	 * 재단 삭제
	 * @param foundationCode
	 */
	public boolean removeFoundation(String foundationCode) {
		boolean isRemove = foundationMapper.removeFoundationCheck(foundationCode);
		if(isRemove) {
			foundationMapper.removeFoundation(foundationCode);
			return true;
		}
		return false;
	}
	
	/**
	 * 재단 검색 결과 조회
	 * @param searchMap
	 * @return
	 */
	public List<Foundation> getFoundationListBySearch(String inputSearchKey
													, String inputSearchValue
													, String startDate
													, String endDate){
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		if(inputSearchKey != null && inputSearchValue != null) {
			switch (inputSearchKey) {
			case "foundationName":
				inputSearchKey = "foundation_name";
				break;
			case "foundationManager":
				inputSearchKey = "foundation_manager";					
				break;
			}
			searchMap.put("inputSearchKey", inputSearchKey);
			searchMap.put("inputSearchValue", inputSearchValue);
		}
		if(startDate != null && endDate != null) {
			searchMap.put("startDate", startDate);
			searchMap.put("endDate", endDate);
		}
		log.info("searchMap: {}", searchMap);
		
		List<Foundation> foundationList = foundationMapper.getFoundationListBySearch(searchMap);
		return foundationList;
	}
	
	/**
	 * 재단 조회
	 * @return List<Foundation>
	 */
	public List<Foundation> getFoundationList(){
		List<Foundation> foundationList = foundationMapper.getFoundationList();
		return foundationList;
	}
	
}
