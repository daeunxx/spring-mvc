package org.example.upload.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.upload.domain.Item;
import org.example.upload.domain.ItemRepository;
import org.example.upload.domain.UploadFile;
import org.example.upload.file.FileStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemRepository itemRepository;
  private final FileStore fileStore;

  @GetMapping("/items/new")
  public String newItem(@ModelAttribute ItemForm form) {
    return "item-form";
  }

  @PostMapping("/items/new")
  public String save(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes)
      throws IOException {
    UploadFile uploadFile = fileStore.storeFile(form.getAttachFile());
    List<UploadFile> uploadFiles = fileStore.storeFiles(form.getImageFiles());

    //데이터베이스에 저장
    Item item = new Item();
    item.setAttachFile(uploadFile);
    item.setImageFiles(uploadFiles);
    itemRepository.save(item);

    redirectAttributes.addAttribute("itemId", item.getId());

    return "redirect:/items/{itemId}";
  }

  @GetMapping("/items/{id}")
  public String items(@PathVariable Long id, Model model) {
    Item item = itemRepository.findById(id);
    model.addAttribute("item", item);
    return "item-view";
  }

  @ResponseBody
  @GetMapping("/images/{filename}")
  public UrlResource downloadImage(@PathVariable String filename) throws MalformedURLException {
    //file: 내부 파일에 접근
    return new UrlResource("file:" + fileStore.getFullPath(filename));
  }

  @GetMapping("/attach/{itemId}")
  public ResponseEntity<Resource> downloadAttach(@PathVariable long itemId)
      throws MalformedURLException {
    Item item = itemRepository.findById(itemId);
    String storeFileName = item.getAttachFile().getStoreFileName();
    String uploadFileName = item.getAttachFile().getUploadFileName();

    UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

    log.info("uploadFileName={}", uploadFileName);

    String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
    String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
        .body(urlResource);
  }
}
