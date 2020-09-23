package jp.co.java_stream_api_like_sql.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.java_stream_api_like_sql.app.response.CategoryAggregationResponse;
import jp.co.java_stream_api_like_sql.app.response.CategoryItemResponse;
import jp.co.java_stream_api_like_sql.domain.dto.CategoryAggregationDto;
import jp.co.java_stream_api_like_sql.domain.dto.CategoryItemDto;
import jp.co.java_stream_api_like_sql.domain.service.CategoryItemService;
import lombok.RequiredArgsConstructor;

/**
 * カテゴリ別商品取得用コントローラクラス
 *
 * @author CHI-3
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/item-category")
public class CategoryItemController {

	private final CategoryItemService categoryItemService;

	/**
	 * カテゴリ別商品一覧を取得
	 *
	 * @return カテゴリ別商品一覧
	 */
	@GetMapping("/item")
	private ResponseEntity<CategoryItemResponse> getCategoryItem(){
		List<CategoryItemDto> categoryItems = categoryItemService.getCategoryItem();
		CategoryItemResponse categoryItemResponse = CategoryItemResponse.builder().categoryItems(categoryItems).build();
		return new ResponseEntity<>(categoryItemResponse, HttpStatus.OK);
	}

	/**
	 * 商品カテゴリ集計を取得
	 *
	 * @return 商品カテゴリ集計
	 */
	@GetMapping("/aggregation")
	private ResponseEntity<CategoryAggregationResponse> getCategoryAggregation(){
		List<CategoryAggregationDto> categoryAggregations = categoryItemService.getCategoryAggregation();
		CategoryAggregationResponse categoryAggregationResponse = CategoryAggregationResponse.builder(). categoryAggregations( categoryAggregations).build();
		return new ResponseEntity<>( categoryAggregationResponse, HttpStatus.OK);
	}

}
