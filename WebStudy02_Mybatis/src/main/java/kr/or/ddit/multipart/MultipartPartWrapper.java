package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * 변환해주는 작업
 */
public class MultipartPartWrapper implements MultipartFile{
	private final Part adaptee;

	public MultipartPartWrapper(Part adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	
	/**
	 * 클라이언트가 읽어야할 파일
	 */
	@Override
	public byte[] getBytes() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}
	
	/**
	 * 파일 MINE 결정 ContentType
	 */
	@Override
	public String getContentType() {		
		return adaptee.getContentType();
	}

	/**
	 *파일 읽기
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return adaptee.getInputStream();
	}
	
	/**
	 * 파트 이름
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return adaptee.getName();
	}
	

	/**
	 * 원본 파일명 
	 */
	@Override
	public String getOriginalFilename() {
		return adaptee.getSubmittedFileName();
	}
	
	
	/**
	 * 파일 사이즈 
	 */
	@Override
	public long getSize() {
		return adaptee.getSize();
	}
	
	/**
	 * 원본 파일명 비어 있을 때 
	 */
	@Override
	public boolean isEmpty() {
		return StringUtils.isBlank(getOriginalFilename());
	}

	/**
	 * 파일 저장을 위한 작업  
	 */
	@Override
	public void transferTo(File dest) throws IOException {
		adaptee.write(dest.getAbsolutePath());
		
	}
	
	
}
