package com.study.product.service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.study.product.dao.ProductDao;
import com.study.product.dto.InsertProductReqDto;
import com.study.product.dto.InsertProductResDto;
import com.study.product.dto.SearchProductResDto;
import com.study.product.vo.ProductVo;

public class ProductService {
	private static ProductService instance;
	private ProductDao productDao;
	
	private ProductService() {
		productDao = ProductDao.getInstance();
	}
	
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
		
		}
		return instance;
	}
	
	public boolean isDuplicatedProductName(String productName) {
		return productDao.findProductByProductName(productName) != null;
	}
	
	public InsertProductResDto addProduct(InsertProductReqDto insertProductReqDto) {
		ProductVo vo = insertProductReqDto.toVo();
		
		int successCount = productDao.save(vo);
		
		return vo.toInsertDto(successCount);
		
	}
	
	public List<SearchProductResDto> searchProducts() {
		List<SearchProductResDto> list = new ArrayList<>();
		
		List<ProductVo> vos = productDao.getProductList();
		
		for(ProductVo vo : vos) {
			list.add(vo.toSearchDto());
		}
		
		return list;
		
//		Stream<String> stream = Stream.of("a", "d", "g", "h");
//		stream.peek(str -> System.out.println(str));
//		
//		List<String> strList = stream.peek(System.out::println).collect(Collectors.toList());
//		
//		
//		return productDao.getProductList().stream()
//				.map(vo -> vo.toSearchDto())
//				.collect(Collectors.toList());
		
	}
	
}