package com.study.product.vo;

import com.study.product.dto.InsertProductResDto;
import com.study.product.dto.SearchProductResDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductVo {
	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;
	
	public InsertProductResDto toInsertDto(int successCount) {
		return InsertProductResDto.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
	
	public SearchProductResDto toSearchDto() {
		return SearchProductResDto.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
	
}