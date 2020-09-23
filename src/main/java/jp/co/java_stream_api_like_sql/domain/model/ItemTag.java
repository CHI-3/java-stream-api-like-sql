package jp.co.java_stream_api_like_sql.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * The persistent class for the item_tags database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name="item_tags")
@NamedQuery(name="ItemTag.findAll", query="SELECT i FROM ItemTag i")
public class ItemTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tag_id")
	private Integer tagId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="is_deleted")
	private Boolean isDeleted;

	@Column(name="tag_name")
	private String tagName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	public ItemTag() {
	}

}