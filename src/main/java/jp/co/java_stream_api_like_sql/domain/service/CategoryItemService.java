package jp.co.java_stream_api_like_sql.domain.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jp.co.java_stream_api_like_sql.domain.dto.CategoryAggregationDto;
import jp.co.java_stream_api_like_sql.domain.dto.CategoryItemDto;
import jp.co.java_stream_api_like_sql.domain.dto.ItemDto;
import jp.co.java_stream_api_like_sql.domain.model.Item;
import jp.co.java_stream_api_like_sql.domain.model.ItemCategory;
import jp.co.java_stream_api_like_sql.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

/**
 * カテゴリ別商品取得用サービスクラス
 *
 * @author CHI-3
 *
 */
@Service
@RequiredArgsConstructor
public class CategoryItemService {

	private final ItemRepository itemRepository;

	/**
	 * カテゴリ別商品一覧を取得（商品カテゴリ優先順位、商品優先順位でソート）
	 *
	 * @return カテゴリ別商品一覧
	 */
	public List<CategoryItemDto> getCategoryItem(){
		List<Item> itemsOrigin = itemRepository.findAll();
		// 1. where:削除フラグがfalse(OFF）のものを取得
		Stream<Item> items = itemsOrigin.stream().filter(i -> !(i.getIsDeleted()));
		// 2. partition by：商品を商品カテゴリでグルーピング
		Map<ItemCategory, List<Item>> itemCategoryMap = items.collect(Collectors.groupingBy(Item::getItemCategory, LinkedHashMap::new, Collectors.toList()));

		/*1, 2を同時に実施する場合
		Map<ItemCategory, List<Item>> itemCategoryMap2 = items.stream().filter(i -> !(i.getIsDeleted())).collect(Collectors.groupingBy(Item::getItemCategory, LinkedHashMap::new, Collectors.toList()));
		*/

		List<CategoryItemDto> categoryItems = new ArrayList<>();
		ModelMapper mMapper = new ModelMapper();

		for(Map.Entry<ItemCategory, List<Item>> ic : itemCategoryMap.entrySet()) {
			// カテゴリ情報をセット
			CategoryItemDto categoryItem = new CategoryItemDto();
			BeanUtils.copyProperties(ic.getKey(), categoryItem);
			// 4-1. group by:対象商品カテゴリに属する商品の件数を取得（count）
			categoryItem.setItemCount(ic.getValue().size());
			// 3-1. order by:ソート（商品:優先順位昇順：優先順位がnullの場合は辞書式順序)
			ic.getValue().sort(Comparator.comparing(Item::getRanking, Comparator.nullsLast(Comparator.naturalOrder())));
			java.lang.reflect.Type itemDtos = new TypeToken<List<ItemDto>>() {}.getType();
			List<ItemDto> itemList = mMapper.map(ic.getValue(), itemDtos);
			categoryItem.setItemList(itemList);
			categoryItems.add(categoryItem);
		}

		// 3-2. order by：ソート（商品カテゴリ:優先順位昇順：優先順位がnullの場合は辞書式順序）
		categoryItems.sort(Comparator.comparing(CategoryItemDto::getRanking, Comparator.nullsLast(Comparator.naturalOrder())));

		return categoryItems;

	}

	/**
	 * カテゴリ別集計を取得（商品カテゴリ優先順位でソート）
	 *
	 * @return カテゴリ別集計
	 */
	public List<CategoryAggregationDto> getCategoryAggregation(){

		List<Item> items = itemRepository.findAll();
		// 4-2. group by
		Map<ItemCategory, List<Item>> itemCategoryMap = items.stream().filter(i -> !(i.getIsDeleted())).collect(Collectors.groupingBy(Item::getItemCategory));

		List<CategoryAggregationDto> categoryAggregations = new ArrayList<>();
		for(Map.Entry<ItemCategory, List<Item>> ic : itemCategoryMap.entrySet()) {

			List<Integer> itemPrices = new ArrayList<>();
			ic.getValue().forEach(i -> itemPrices.add(i.getItemPrice()));

			CategoryAggregationDto categoryAggregation = new CategoryAggregationDto();
			BeanUtils.copyProperties(ic.getKey(), categoryAggregation);
			// 4-2-1. count
			categoryAggregation.setItemCount(itemPrices.stream().count());
			// 4-2-2. sum
			categoryAggregation.setPriceSum(itemPrices.stream().mapToInt(Integer::intValue).sum());
			// 4-2-3. average
			categoryAggregation.setPriceAverage(itemPrices.stream().mapToInt(Integer::intValue).average().orElse(Double.NaN));
			// 4-2-4. max
			categoryAggregation.setPriceMax(itemPrices.stream().mapToInt(Integer::intValue).max().orElse(0));
			// 4-2-5. min
			categoryAggregation.setPriceMin(itemPrices.stream().mapToInt(Integer::intValue).min().orElse(0));

			categoryAggregations.add(categoryAggregation);

		}

		categoryAggregations.sort(Comparator.comparing(CategoryAggregationDto::getRanking, Comparator.nullsLast(Comparator.naturalOrder())));

		return categoryAggregations;

	}

}
