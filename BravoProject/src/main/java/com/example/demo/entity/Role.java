package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
	@JsonProperty("User") USER,
	@JsonProperty("Admin") ADMIN
}
