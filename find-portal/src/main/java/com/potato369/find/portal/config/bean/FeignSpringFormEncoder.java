package com.potato369.find.portal.config.bean;

import static feign.form.ContentType.MULTIPART;
import static java.util.Collections.singletonMap;

import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import lombok.val;
// 参考1：https://blog.csdn.net/qq_37119911/article/details/106520571
// 参考2：https://blog.csdn.net/ytzzh0726/article/details/79467843?dist_request_id=&depth_1-
public class FeignSpringFormEncoder extends FormEncoder {

	/**
	 * Constructor with the default Feign's encoder as a delegate.
	 */
	public FeignSpringFormEncoder() {
		this(new Encoder.Default());
	}

	/**
	 * Constructor with specified delegate encoder.
	 *
	 * @param delegate delegate encoder, if this encoder couldn't encode object.
	 */
	public FeignSpringFormEncoder(Encoder delegate) {
		super(delegate);
		val processor = (MultipartFormContentProcessor) getContentProcessor(MULTIPART);
		processor.addFirstWriter(new SpringSingleMultipartFileWriter());
		processor.addFirstWriter(new SpringManyMultipartFilesWriter());
	}

	@Override
	public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
		if (bodyType.equals(MultipartFile.class)) {
			MultipartFile file = (MultipartFile) object;
			Map<String, Object> data = singletonMap(file.getName(), object);
			super.encode(data, MAP_STRING_WILDCARD, template);
		} else if (bodyType.equals(MultipartFile[].class)) {
			// MultipartFile数组处理
			MultipartFile[] file = (MultipartFile[]) object;
			if (file != null) {
				Map<String, Object> data = singletonMap(file.length == 0 ? "" : file[0].getName(), object);
				super.encode(data, MAP_STRING_WILDCARD, template);
				return;
			}
		} else {
			super.encode(object, bodyType, template);
		}
	}
}
