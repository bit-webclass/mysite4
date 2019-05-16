package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

@Service
public class FileUploadService {
	
	
	public FileVo restore(MultipartFile file) {
		String saveDir = "D:\\bitStudy\\upload";
		
		//오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName: " + exName);
		
		//저장파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName: " + saveName);
		
		//파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: " + fileSize);
		
		FileVo fileVo = new FileVo(filePath, orgName, saveName, fileSize);
		System.out.println(fileVo.toString());
		
		//파일 서버 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream( saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);		
			
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//파일 DB등록
		
		return fileVo;
	}

}
