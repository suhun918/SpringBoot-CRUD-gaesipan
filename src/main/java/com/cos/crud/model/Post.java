package com.cos.crud.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter
@AllArgsConstructor
@NoArgsConstructor
@Builder// 얘는 나중에 설명 해주신다고 한다
public class Post {
	private int id;
	private String title;
	private String content;
	private int userId; //FK 이번 프로젝트에서는 강제로 값을 넣고 할 것
	private Timestamp createDate;
}
