package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 멀티 파트 요청에 포함된, part 하나의 정보를 캡슐화할 객체에 대한 정의
 * Part api랑 비슷함
 */
public interface MultipartFile {
	public byte[] getBytes() throws IOException;
	public String getContentType();
	public InputStream getInputStream() throws IOException;
	public String getName();
	//원본 파일명 
	public String getOriginalFilename();
	public long getSize();
	public boolean isEmpty();
	public void transferTo(File dest) throws IOException;
}
