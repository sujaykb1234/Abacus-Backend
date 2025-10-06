package com.abacus.franchise.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abacus.franchise.model.StoredImages;


@Service
public interface S3BucketService {

	StoredImages storeFile(String filename, InputStream content, long length,Integer recordId);

	ByteArrayOutputStream downloadFile(String blobitem);

	List<String> listFiles();
	
	void deleteFile(String imageUrl);

}
