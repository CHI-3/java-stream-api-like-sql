package jp.co.java_stream_api_like_sql.domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the items database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name="items")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
	private Integer itemId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="end_on")
	private Date endOn;

	@Column(name="is_deleted")
	private Boolean isDeleted;

	@Column(name="is_open")
	private Boolean isOpen;

	@Column(name="item_explanation")
	private String itemExplanation;

	@Column(name="item_image")
	private String itemImage;

	@Column(name="item_name")
	private String itemName;

	@Column(name="item_price")
	private Integer itemPrice;

	@Column(name="item_tag")
	private String itemTag;

	private Integer ranking;

	@Temporal(TemporalType.DATE)
	@Column(name="start_on")
	private Date startOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	//bi-directional many-to-one association to ItemCategory
	@ManyToOne
	@JoinColumn(name="category_id", insertable = false, updatable = false)
	private ItemCategory itemCategory;

	public Item() {
	}

}