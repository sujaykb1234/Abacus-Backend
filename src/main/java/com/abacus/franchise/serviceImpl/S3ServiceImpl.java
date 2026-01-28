//package com.abacus.franchise.serviceImpl;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.abacus.franchise.model.Counts;
//import com.abacus.franchise.model.StoredImages;
//import com.abacus.franchise.repo.StoredImagesRepo;
//import com.abacus.franchise.service.S3BucketService;
//
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.core.sync.ResponseTransformer;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
//import software.amazon.awssdk.services.s3.model.GetObjectRequest;
//import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
//import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.S3Object;
//
//@Service
//public class S3ServiceImpl implements S3BucketService {
//
//	@Autowired
//	CountsServiceImpl countsServiceImpl;
//
//	@Autowired
//	StoredImagesRepo imageRepo;
////
//	@Value("${aws.s3.bucket-name}")
//	private String bucketName;
//	@Value("${aws.s3.region}")
//	private String s3Region;
//	@Value("${aws.s3.access-key}")
//	private String s3AccessKey;
//	@Value("${aws.s3.secret-key}")
//	private String s3SecretKey;
//	@Value("${aws.s3.url}")
//	private String s3Url;
//
//	private S3Client s3Client() {
//		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(s3AccessKey, s3SecretKey);
//		return S3Client.builder().region(Region.of(s3Region))
//				.credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//				.endpointOverride(URI.create("https://s3.us-east-1.amazonaws.com")).build();
//	}
//
//	@Override
//	public List<String> listFiles() {
//		S3Client s3Client = s3Client();
//		ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket(bucketName).build();
//		ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsV2Request);
//		List<String> files = new ArrayList<>();
//		for (S3Object s3Object : listObjectsV2Response.contents()) {
//			files.add(s3Object.key());
//		}
//
//		return files;
//	}
//
//	@Override
//	public ByteArrayOutputStream downloadFile(String keyName) {
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		S3Client s3Client = s3Client();
//		GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(keyName).build();
//
//		s3Client.getObject(getObjectRequest, ResponseTransformer.toOutputStream(outputStream));
//
//		return outputStream;
//	}
//
//
//
//	@Override
//	public StoredImages storeFile(String filename, InputStream content, long length, Integer recordId) {
//		Counts imgCount = countsServiceImpl.getImgCount(recordId);
//		String abbreviation = imgCount.getAbbreviation();
//		Long count = imgCount.getCount();
//		String countString = count.toString();
//
//		String finalFilename = abbreviation + countString + filename;
//		S3Client s3Client = s3Client();
//
//		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(finalFilename)
//				.contentLength(length).build();
//
//		RequestBody requestBody = RequestBody.fromInputStream(content, length);
//		s3Client.putObject(putObjectRequest, requestBody);
//
//		StoredImages images = new StoredImages();
//
//		String fullUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, s3Region, finalFilename);
//
//		if (recordId == 1) {
//			images.setProfile_image_name(finalFilename);
//			images.setProfile_image_link(fullUrl);
//		} else if (recordId == 2) {
//			images.setId_proof_image_name(finalFilename);
//			images.setId_proof_image_link(fullUrl);
//		} else if (recordId == 3) {
//			images.setProfile_image_name(finalFilename);
//			images.setProfile_image_link(fullUrl);
//		} else if (recordId == 4) {
//			images.setId_proof_image_name(finalFilename);
//			images.setId_proof_image_link(fullUrl);
//		} else if (recordId == 5) {
//			images.setProfile_image_name(finalFilename);
//			images.setProfile_image_link(fullUrl);
//		} else if (recordId == 6) {
//			images.setPdfName(finalFilename);
//			images.setPdfLink(fullUrl);
//		}
//		else if (recordId == 7) {
//			images.setPdfName(finalFilename);
//			images.setPdfLink(fullUrl);
//		}
//
//		imgCount.setCount(count + 1);
//		countsServiceImpl.saveImgCount(imgCount);
//
//		return imageRepo.save(images);
//	}
//
//	// SJ
//	@Override
//	public void deleteFile(String imageUrl) {
//		String keyName = imageUrl.replace(s3Url, "");
//		System.out.println("Deleting S3 Object with Key: " + keyName);
//		S3Client s3Client = s3Client();
//		DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(keyName).build();
//		try {
//			s3Client.deleteObject(deleteObjectRequest);
//			System.out.println("Image deleted successfully.");
//		} catch (Exception e) {
//			System.err.println("Error deleting image: " + e.getMessage());
//		}
//	}
//
//}
