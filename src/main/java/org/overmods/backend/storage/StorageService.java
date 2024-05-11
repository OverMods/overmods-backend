package org.overmods.backend.storage;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    void init();
    String store(MultipartFile file) throws StorageFailure, ExtensionNotAllowed;
    Path load(String filename);
}
