package com.stylclos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDTO {

	private String name, type, url;
	private byte[] imgData;

}