package com.ysb.config;

import io.swagger.models.Contact;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wushenjun on 2017-05-23.
 */
@ConfigurationProperties(prefix = "ysb.boot.swagger2")
public class Swagger2Properties {
	private boolean enable = true;
	private String basePackage = "com.ysb";
	private ApiInfo apiInfo = new ApiInfo();

	public static class ApiInfo{
		private String version = "1.0";
		private String title = "项目api文档";
		private String description = "详情请点开各个接口。";
		private Contact contact = new Contact();

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Contact getContact() {
			return contact;
		}

		public void setContact(Contact contact) {
			this.contact = contact;
		}
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public ApiInfo getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}
}
