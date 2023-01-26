package com.store.services;

public interface ImageService {

    byte[] compressImage(byte[] image);

    byte[] decompressImage(byte[] image);
}
