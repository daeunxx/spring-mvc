package org.example.upload.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.example.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {

  @Value("${file.dir}")
  private String fileDir;

  public String getFullPath(String fileName) {
    return fileDir + fileName;
  }

  public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

    List<UploadFile> storeFiles = new ArrayList<>();

    for (MultipartFile multipartFile : multipartFiles) {
      if (!multipartFile.isEmpty()) {
        UploadFile uploadFile = storeFile(multipartFile);
        storeFiles.add(uploadFile);
      }
    }
    return storeFiles;
  }

  public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
    if (multipartFile.isEmpty()) {
      return null;
    }

    String originalFilename = multipartFile.getOriginalFilename();
    String storeFileName = createStoreFileName(originalFilename);

    multipartFile.transferTo(new File(getFullPath(storeFileName)));

    return new UploadFile(originalFilename, storeFileName);
  }

  private static String createStoreFileName(String originalFilename) {
    String uuid = UUID.randomUUID().toString();
    String ext = extractExt(originalFilename);
    return uuid + "." + ext;
  }

  private static String extractExt(String originalFilename) {
    int pos = originalFilename.lastIndexOf(".");
    return originalFilename.substring(pos + 1);
  }
}
