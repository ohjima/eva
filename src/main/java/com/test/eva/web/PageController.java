package com.test.eva.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.UUID;

import com.innorix.transfer.InnorixUpload;
import com.innorix.transfer.ActionBase.ActionResult;

@Controller
public class PageController {

  @RequestMapping({ "/web/{PATH1}.inf" })
  public String webpath1(@PathVariable("PATH1") String PATH1, Model model) throws Exception {
    System.out.println("pages/web/" + PATH1);
    return "pages/web/" + PATH1;
  }

  @RequestMapping({ "/web/{PATH1}/{PATH2}" })
  public String webpath2(@PathVariable("PATH1") String PATH1, @PathVariable("PATH2") String PATH2, Model model)
      throws Exception {
    return "pages/web/" + PATH1 + "/" + PATH2;
  }

  @RequestMapping({ "/web/{PATH1}/{PATH2}/{PATH3}" })
  public String webpath3(@PathVariable("PATH1") String PATH1, @PathVariable("PATH2") String PATH2,
      @PathVariable("PATH3") String PATH3, Model model) throws Exception {
    return "pages/web/" + PATH1 + "/" + PATH2 + "/" + PATH3;
  }

  @RequestMapping({ "/fileupload" })
  public String upload(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    int maxPostSize = 2147483000;
    String responseMessage = new String();
    ActionResult result;

    try {
      // String directory = InnorixUpload.getServletAbsolutePath(request);
      // directory = directory.substring(0, directory.lastIndexOf("/") + 1) + "data";
      // InnorixUpload uploader = new InnorixUpload(request, response, maxPostSize,
      // "UTF-8", directory);

      // uploader 객체선언
      InnorixUpload uploader = new InnorixUpload(request, response, maxPostSize, "C:/TOS/Attach/");

      /*
       * maxPostSize 한번에 업로드 가능한 최대 POST 데이터 사이즈(byte) 입니다.
       * 최소값 10485760(10MB) ~ 최대값 2147482624(2GB - 1024)
       * 최대값을 넘어서는 빅데이터 파일도 내부 처리에 의해 용량 제한없이 전송 됩니다.
       * 
       * directory 파일이 저장될 시스템상 전체 경로이며 아래 형식 입니다.
       * 디렉토리 구분은 윈도우, 유닉스 모두 "/" 문자를 사용 합니다.
       * 윈도우 예시 - C:/storage/path1/path2/data
       * 유닉스 예시 - /storage/path1/path2/data
       */

      String _action = uploader.getParameter("_action"); // 동작 플래그
      String _orig_filename = uploader.getParameter("_orig_filename"); // 원본 파일명
      String _new_filename = uploader.getParameter("_new_filename"); // 저장 파일명
      String _filesize = uploader.getParameter("_filesize"); // 파일 사이즈
      String _start_offset = uploader.getParameter("_start_offset"); // 파일저장 시작지점
      String _end_offset = uploader.getParameter("_end_offset"); // 파일저장 종료지점
      String _filepath = uploader.getParameter("_filepath"); // 파일 저장경로
      String _el = uploader.getParameter("el"); // 컨트롤 엘리먼트 ID
      String _type = uploader.getParameter("type"); // 커스텀 정의 POST Param 1
      String _part = uploader.getParameter("part"); // 커스텀 정의 POST Param 2
      String _transferId = uploader.getParameter("_transferId"); // TransferId
      String _run_retval = uploader.run();

      // // UUID 형식으로 저장할 파일명 설정
      // uploader.setFileName(UUID.randomUUID().toString());

      // result = uploader.runForSpring();
      // responseMessage = result.getXml();

      // // 업로드 완료
      // if (uploader.isUploadDone()) {
      // System.out.println("========== UploadDone : " + System.currentTimeMillis() +
      // "==========");
      // System.out.println("_orig_filename = " +
      // uploader.getParameter("_orig_filename")); // 원본 파일명
      // System.out.println("_save_filename = " +
      // uploader.getParameter("_new_filename")); // 저장 파일명
      // System.out.println("_filesize = " + uploader.getParameter("_filesize")); //
      // 파일 사이즈
      // System.out.println("_filepath = " + uploader.getParameter("_filepath")); //
      // 파일 저장경로
      // }
    } catch (Exception e) {
      System.out.println(e);
    }

    return "";
  }
}
