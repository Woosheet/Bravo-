package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
	@JsonProperty("User") USER,
	@JsonProperty("Owner") OWNER,
	@JsonProperty("Admin") ADMIN
}
