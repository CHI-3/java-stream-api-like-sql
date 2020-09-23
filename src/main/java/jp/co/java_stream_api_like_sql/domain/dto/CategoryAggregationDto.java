package jp.co.java_stream_api_like_sql.domain.dto;

import lombok.Data;

@Data
/**
 * 商品カテゴリ集計
 *
 * @author CHI-3
 */
public class CategoryAggregationDto {
	/** 商品カテゴリID */
	private Integer categoryId;
	/** 商品カテゴリ名 */
	private String categoryName;
	/** 優先順位 */
	private Short ranking;
	/** 商品件数 */
	private long itemCount;
	/** 価格合計 */
	private int priceSum;
	/** 価格平均 */
	private double priceAverage;
	/** 最高価格 */
	private int priceMax;
	/** 最低価格 */
	private int priceMin;
}
