package com.dotori.backend.script.model.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dotori.backend.role.model.entity.Role;
import com.dotori.backend.scene.model.entity.Scene;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "script")
public class Script {
	@Id
	@Column(name = "script_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long scriptId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "scene_id", nullable = false)
	private Scene scene;

	@Column(name = "script_order")
	private int scriptOrder;

	@Column(name = "content")
	private String content;
}
