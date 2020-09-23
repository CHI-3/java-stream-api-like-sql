package jp.co.java_stream_api_like_sql.app.response;

import java.util.List;

import jp.co.java_stream_api_like_sql.domain.dto.CategoryItemDto;
import lombok.Builder;
import lombok.Getter;

/**
 * カテゴリ別商品一覧返却用レスポンスクラス
 *
 * @author CHI-3
 *
 */
@Getter
@Builder
public class CategoryItemResponse {
	/** カテゴリ別商品一覧 */
	List<CategoryItemDto> categoryItems;
}
