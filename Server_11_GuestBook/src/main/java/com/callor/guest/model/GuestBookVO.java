package com.callor.guest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GuestBookVO {
	
	private Long gb_seq;	//Big INT
	private String gb_date;
	private String gb_time;
	private String gb_writer;
	private String gb_email;
	private String gb_password;
	private String gb_content;

}
