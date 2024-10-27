/**
 *
 */
package com.gl.sems.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * Response class for sending an appropriate response
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Data
public class ResponseDto implements Serializable {
	/**
	 * Properties declaration
	 */
	private static final long serialVersionUID = 1L;
	private String response;

	/**
	 * @param response
	 */
	public ResponseDto(String response) {
		super();
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResponseDto [response=" + response + "]";
	}

}
