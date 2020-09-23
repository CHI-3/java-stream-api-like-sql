package jp.co.java_stream_api_like_sql.app.response;

import java.util.List;

import jp.co.java_stream_api_like_sql.domain.dto.CategoryAggregationDto;
import lombok.Builder;
import lombok.Getter;

/**
 * 商品カテゴリ集計レスポンスクラス
 *
 * @author CHI-3
 *
 */
@Getter
@Builder
public class CategoryAggregationResponse {
	/** 商品カテゴリ集計 */
	private List<CategoryAggregationDto> categoryAggregations;
}
