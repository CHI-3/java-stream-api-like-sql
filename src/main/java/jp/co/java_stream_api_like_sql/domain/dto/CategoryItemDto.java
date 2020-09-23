package jp.co.java_stream_api_like_sql.domain.dto;

import java.util.List;

import lombok.Data;

/**
 * カテゴリ別商品一覧
 *
 * @author CHI-3
 *
 */
@Data
public class CategoryItemDto {
	private Integer categoryId;
	/** カテゴリ名 */
	private String categoryName;
	/** 商品一覧 */
	private List<ItemDto> itemList;
	/** 商品数 */
	private int itemCount;
	/** 優先順位 */
	private Short ranking;
}
