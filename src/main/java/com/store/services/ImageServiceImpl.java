package com.store.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Override
    public byte[] compressImage(byte[] image) {
        var deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(image);
        deflater.finish();

        var outputStream = new ByteArrayOutputStream(image.length);
        var tmp = new byte[4 * 1024];

        while (!deflater.finished()) {
            var size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            log.error("An error ocurred when trying to compress the image!", e.getCause());
        }
        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompressImage(byte[] image) {
        var inflater = new Inflater();
        inflater.setInput(image);

        var outputStream = new ByteArrayOutputStream(image.length);
        var tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                var count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            log.error("An error ocurred when trying to decompress the image!", e.getCause());
        }
        return outputStream.toByteArray();
    }
}
