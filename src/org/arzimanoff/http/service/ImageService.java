package org.arzimanoff.http.service;

import lombok.SneakyThrows;
import org.arzimanoff.http.util.PropertiesUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class ImageService {
    private static final ImageService INSTANCE = new ImageService();

    private ImageService() {
    }

    private final String basePath = PropertiesUtil.get("image.base.url");

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent){
        var imageFullPath = Path.of(basePath, imagePath);
        try(imageContent){
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath,
                    imageContent.readAllBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        }
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}
