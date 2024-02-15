package com.study.servlet_study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {
	String username;
	String password;
	String email;
	String name;

}