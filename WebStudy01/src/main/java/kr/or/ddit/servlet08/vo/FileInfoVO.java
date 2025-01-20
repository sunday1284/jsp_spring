package kr.or.ddit.servlet08.vo;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Objects;

public class FileInfoVO implements Serializable{
	private String uploader;
	private transient String originalFilename; //직렬화제외 -> 안보이게
	private String savename;
	private Path filePath;
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public Path getFilePath() {
		return filePath;
	}
	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
	@Override
	public int hashCode() {
		return Objects.hash(filePath);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileInfoVO other = (FileInfoVO) obj;
		return Objects.equals(filePath, other.filePath);
	}
	
	@Override
	public String toString() {
		return "FileInfoVO [uploader=" + uploader + ", savename=" + savename + ", filePath=" + filePath + "]";
	}
	
	
	
}
