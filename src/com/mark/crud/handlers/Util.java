package com.mark.crud.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年11月5日下午9:01:35
 * @Remarks:
 */

@Controller
public class Util {

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile mulfile) {

		System.out.println("上傳開始...");
		long startTime=System.currentTimeMillis();
		
		String savePath = request.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);

		if (!file.exists() && !file.isDirectory()) {
			System.out.println("目錄不存在");
			file.mkdir();
		}
		InputStream io = null;
		FileOutputStream fos = null;
		try {

			io = mulfile.getInputStream();
			fos = new FileOutputStream(savePath + File.separator + mulfile.getOriginalFilename());
			this.uploadFile(io, fos);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (io != null)
						io.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long endTime=System.currentTimeMillis();
		System.out.println("上傳成功！總共花費時間："+(endTime-startTime)+"mm");
		return "sucess";
	}

	/**
	 * Description:節點流
	 * @author MarkLin
	 * @Date:2019年11月6日下午4:16:41
	 * @Remarks:
	 */
	public static void uploadFile(InputStream in, OutputStream out) throws IOException {
		synchronized (in) {
			synchronized (out) {
				byte[] buffer = new byte[256];
				while (true) {
					int bytesRead = in.read(buffer);
					if (bytesRead == -1)
						break;
					out.write(buffer, 0, bytesRead);
				}
			}
		}
	}
	

}
