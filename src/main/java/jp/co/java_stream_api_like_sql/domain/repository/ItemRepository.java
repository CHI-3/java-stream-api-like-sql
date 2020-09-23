package jp.co.java_stream_api_like_sql.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.java_stream_api_like_sql.domain.model.Item;

/**
 * 商品に関するリポジトリインターフェース
 *
 * @author CHI-3
 *
 */
@Repository
public interface ItemRepository extends JpaRepository <Item, Integer>{
}
