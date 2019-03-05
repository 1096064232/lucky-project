/**
 * 
 */
package com.lucky.core.property.security.verification;


import com.lucky.core.security.verification.image.ImageTypeEnum;

/**
 * 图形验证码配置项
 */
public class ImageCodeProperties extends SmsCodeProperties {
	
	public ImageCodeProperties() {
		setLength(4);
	}
	/**
	 *  图形验证码的图片的长度
	 */
	private int width = 67;

	/**
	 *  图形验证码的图片的高度
	 */
	private int height = 23;

	/**
	 *  验证码的类型
	 */
	private ImageTypeEnum imageTypeEnum = ImageTypeEnum.N;

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public ImageTypeEnum getImageTypeEnum() {
		return imageTypeEnum;
	}

	public void setImageTypeEnum(ImageTypeEnum imageTypeEnum) {
		this.imageTypeEnum = imageTypeEnum;
	}
}
