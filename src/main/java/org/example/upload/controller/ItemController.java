package org.example.upload.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.upload.domain.Item;
import org.example.upload.domain.ItemRepository;
import org.example.upload.domain.UploadFile;
import org.example.upload.file.FileStore;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
}
