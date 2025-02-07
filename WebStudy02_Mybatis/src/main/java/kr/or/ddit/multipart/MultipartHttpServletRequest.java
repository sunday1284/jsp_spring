package kr.or.ddit.multipart;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class MultipartHttpServletRequest extends HttpServletRequestWrapper {
	private Map<String, MultipartFile> fileMap;
	
	public MultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		parseRequest(request);
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
//		Part --> MultipartFile
	  fileMap = new HashMap<>();
	  Collection<Part> parts =request.getParts();
	  for(Part single: parts) {
	
		  if(single.getContentType()==null) continue;
			  
		  
		  // part 객체를 MultipartFile로 변환
		  MultipartFile file = new MultipartPartWrapper(single);
		  fileMap.put(file.getName(), file);
	  }
	}
	
	//파일 맵을 꺼내기위한 getter 호출
	public Map<String, MultipartFile> getFileMap() {
		return fileMap;
	}
	//일부만 꺼내기 위함
	public MultipartFile getFile(String partName) {
		 return fileMap.get(partName);

	}
}
