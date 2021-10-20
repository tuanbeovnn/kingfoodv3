package com.kingfood.backend.domains.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "apis")
public class ApiEntity extends BaseEntity {

	@Column(name = "name")
	private String name;
	
	@Column(name = "http_method")
	private String httpMethod;

	@Column(name = "pattern")
	private String pattern;
	
	@Column(name = "is_required_access_token")
	private Boolean isRequiredAccessToken;
	
	@Column(name="should_check_permission")
	private Boolean shouldCheckPermission;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "api_role",
			joinColumns = @JoinColumn(name = "api_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roles = new ArrayList<>();
	
	public Boolean getIsRequiredAccessToken() {
		return isRequiredAccessToken;
	}

	public void setIsRequiredAccessToken(Boolean requiredAccessToken) {
		isRequiredAccessToken = requiredAccessToken;
	}
}