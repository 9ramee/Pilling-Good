package ks46team04.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks46team04.admin.dto.Goods;
import ks46team04.admin.dto.GoodsCategory;
import ks46team04.admin.mapper.GoodsMapper;
import ks46team04.admin.service.GoodsService;

@Controller
@RequestMapping("/admin/goods")
public class GoodsController {
	
	
	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

	private final GoodsService goodsService;
	private final GoodsMapper goodsMapper;
	
	public GoodsController(GoodsService goodsService, GoodsMapper goodsMapper) {
		this.goodsService = goodsService;
		this.goodsMapper = goodsMapper;
	}
	
	/**
     * 상품 삭제
     * @param model
     * @return
     */
    @PostMapping("/remove_goods")
    @ResponseBody
    public List<String> removeGoods(@RequestParam(value="valueArr[]") List<String> valueArr) {

        log.info("valueArr: {}", valueArr);
        goodsService.removeGoods(valueArr);

        return valueArr;
    }
	
	/**
	 * 상품수정 @PostMapping
	 * @param goods
	 * @return
	 */
	@PostMapping("/modify_goods")
	public String modifyGoods(Goods goods, HttpSession session) {
		
		String goodsUpdId = (String) session.getAttribute("SID");
	    log.info("goodsUpdId: {}", goodsUpdId);
		
	    goods.setGoodsUpdId(goodsUpdId);
	    log.info("goodsInfo: {}", goods);
	    
		goodsService.modifyGoods(goods);
		
		return "redirect:/admin/goods/goods_list";
	}
	
	/**
	 * 상품 수정 @GetMapping
	 * @param model
	 * @param goodsCode
	 * @return
	 */
	@GetMapping("/modify_goods")
	public String modifyGoods(Model model, @RequestParam(name="goodsCode") String goodsCode) {
	    
		Goods goodsInfo = goodsService.getGoodsInfoByCode(goodsCode);
		log.info("goodsInfo: {}", goodsInfo);
		
		List<GoodsCategory> goodsCategoryList = goodsService.getGoodsCategoryList();
		log.info("goodsCategoryList: {}", goodsCategoryList);
		
		model.addAttribute("title", "상품 수정");
		model.addAttribute("goodsInfo", goodsInfo);
		model.addAttribute("goodsCategoryList", goodsCategoryList);
		
		return "admin/goods/modify_goods";
	}
	
	/**
	 * 상품 제조사 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/modal_goods_company_search")
	public String getGoodsCompanyList(Model model) {
		
		List<String> goodsCompanyList = goodsService.getGoodsCompanyList();
		log.info("goodsCompanyList: {}", goodsCompanyList);
		
		model.addAttribute("title", "상품 제조사 조회");
		model.addAttribute("goodsCompanyList", goodsCompanyList);
		
		return "admin/goods/modal_goods_company_search";
	}
	
	/**
	 * 상품명 중복체크
	 * @param goodsName
	 * @return
	 */
	@PostMapping("/goodsNameCheck")
	@ResponseBody
	public boolean goodsNameCheck(@RequestParam(name="goodsName") String goodsName) {
		boolean checked = true;
		
		checked = goodsMapper.goodsNameCheck(goodsName);
		
		return checked;
	}
	
	/**
	 * 상품 등록 @PostMapping
	 * @param goods
	 * @return
	 */
	@PostMapping("/add_goods")
	public String addGoods(Goods goods, HttpSession session) {
		
		String goodsRegId = (String) session.getAttribute("SID");
	    log.info("goodsRegId: {}", goodsRegId);

	    goods.setGoodsRegId(goodsRegId); // 등록자 아이디 필드에 goodsRegId 설정
	    log.info("goods: {}", goods);
	    
	    goodsService.addGoods(goods);

	    return "redirect:/admin/goods/goods_list";
	}

	/**
	 * 상품 등록 @GetMapping
	 * @param model
	 * @return
	 */
	@GetMapping("/add_goods")
	public String addGoods(Model model) {

		log.info("model: {}", model);
		
		List<GoodsCategory> goodsCategoryList = goodsService.getGoodsCategoryList();
		log.info("goodsCategoryList: {}", goodsCategoryList);
		
		List<String> goodsCompanyList = goodsService.getGoodsCompanyList();
		log.info("goodsCompanyList: {}", goodsCompanyList);
		
		model.addAttribute("title", "상품 등록");
		model.addAttribute("goodsCategoryList", goodsCategoryList);
		model.addAttribute("goodsCompanyList", goodsCompanyList);
		
		return "admin/goods/add_goods";
	}
	
	/**
	 * 상품 검색 결과 조회
	 * @param model
	 * @param searchKey
	 * @param searchValue
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GetMapping("/searchList")
	@ResponseBody
	public List<Goods> search(@RequestParam(value="searchKey", required = false) String searchKey 
							, @RequestParam(value="searchValue", required = false) String searchValue
							, @RequestParam(value="startDate", required = false) String startDate
							, @RequestParam(value="endDate", required = false) String endDate) {
		
		log.info("searchKey: {}", searchKey);
		log.info("searchValue: {}", searchValue);
		log.info("startDate: {}", startDate);
		log.info("endDate: {}", endDate);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if(searchKey != null && searchValue != null) {
			
			switch (searchKey) {
			case "goodsName":
				searchKey = "goods_name";
				break;
			case "goodsCategory":
				searchKey = "goods_category";					
				break;
			case "goodsCompany":
				searchKey = "goods_company";					
				break;
			}
			
			paramMap.put("searchKey", searchKey);
			paramMap.put("searchValue", searchValue);
		}
		if(startDate != null && endDate != null) {
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			
		}
		log.info("paramMap: {}", paramMap);
		
		List<Goods> goodsList = goodsService.getGoodsListBySearch(paramMap);
		log.info("goodsList: {}", goodsList);
		
		return goodsList;
	}
	
	/**
	 * 상품 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/goods_list")
	public String getGoodsList(Model model) {
		
		List<Goods> goodsList = goodsService.getGoodsList();
		List<GoodsCategory> goodsCategoryList = goodsService.getGoodsCategoryList();
		
		log.info("goodsList: {}", goodsList);
		log.info("goodsCategoryList: {}", goodsCategoryList);
		
		model.addAttribute("title", "상품 목록");
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsCategoryList", goodsCategoryList);
		
		return "admin/goods/goods_list";
	}
	
}
