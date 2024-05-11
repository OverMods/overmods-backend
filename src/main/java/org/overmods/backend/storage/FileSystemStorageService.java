package org.overmods.backend.storage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class FileSystemStorageService implements StorageService {
    private final StorageProperties storageProperties;

    @Override
    public void init() {
        try {
            Files.createDirectories(Path.of(storageProperties.getLocation()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // https://www.baeldung.com/java-uuid
    private static final Random random = new Random();
    private static UUID generateRandomUUID() {
        long lowPart = 0x8000000000000000L | (random.nextLong() & 0x3FFFFFFFFFFFFFFFL);

        long timeMillis = System.currentTimeMillis();
        long highPart = ((timeMillis & 0x0000_0000_FFFF_FFFFL) << 32)
                | (((timeMillis >> 32) & 0xFFFF) << 16)
                | (1 << 12)
                | (((timeMillis >> 48) & 0x0FFF));

        return new UUID(highPart, lowPart);
    }

    private static final Pattern EXTENSION_PATTERN = Pattern.compile("\\.[0-9a-z]+$");
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
            ".png", ".jpg", ".jpeg", ".gif",
            ".zip", ".rar", ".7z"
    );

    @Override
    public String store(MultipartFile file) throws StorageFailure, ExtensionNotAllowed {
        String oldFileName = file.getOriginalFilename();
        Matcher matcher = EXTENSION_PATTERN.matcher(oldFileName);
        if (!matcher.matches()) {
            throw new ExtensionNotAllowed();
        }

        String extension = matcher.group();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new ExtensionNotAllowed();
        }

        // generate here random but unique filename, then store
        String newFileName = generateRandomUUID() + extension;
        Path newPath = Path.of(storageProperties.getLocation(), newFileName);

        try {
            try (InputStream input = file.getInputStream()) {
                Files.copy(input, newPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageFailure(e);
        }

        // return filename, which would be used in database
        return newFileName;
    }

    @Override
    public Path load(String filename) {
        return Path.of(storageProperties.getLocation(), filename);
    }
}
