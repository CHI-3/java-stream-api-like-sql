package jp.co.java_stream_api_like_sql.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the item_categories database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name="item_categories")
@NamedQuery(name="ItemCategory.findAll", query="SELECT i FROM ItemCategory i")
public class ItemCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="category_name")
	private String categoryName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="is_deleted")
	private Boolean isDeleted;

	private Short ranking;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="itemCategory")
	private List<Item> items;

	public ItemCategory() {
	}

}