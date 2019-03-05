/**
 * 
 */
package com.lucky.core.security.verification.image;

import com.lucky.core.security.verification.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 *  图形验证码
 */
public class ImageValidateCode extends ValidateCode {

	private static final long serialVersionUID = 2119865090108870172L;

	private BufferedImage image;
	
	public ImageValidateCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageValidateCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
