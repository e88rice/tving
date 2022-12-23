package com.koreait.tving.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.*;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.annotation.security.PermitAll;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
@RestController
public class VideoController {

//    @GetMapping("/test")
//    public Resource byteSample(@RequestHeader HttpHeaders httpHeaders) throws IOException{
//        FileUrlResource resource = new FileUrlResource("C:\\Users\\Administrator\\Desktop\\intelliJ_LHJ\\tving\\src\\main\\resources\\static\\videos\\program\\재벌집막내아들\\1.mp4");
//        List<ResourceRegion> resourceRegions = HttpRange.toResourceRegions(httpHeaders.getRange(), resource);
//        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse())
//
//        return new ByteArrayResource(new byte[] {'a', 'b'});
//    }

    @PermitAll
    @GetMapping(value = "/video", produces = "video/mp4") // produces = 헤더 값(미디어 타입)을 지정
    public Resource playVideo() throws IOException {
        return new ByteArrayResource(
                FileCopyUtils.copyToByteArray(
                        Files.newInputStream(
                                Paths.get("src/main/resources/static/videos/program/재벌집막내아들/1.mp4"))));
    }

}
