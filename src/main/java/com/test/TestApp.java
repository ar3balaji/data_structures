package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestApp {

	public static void main(String[] args) throws AmazonServiceException, SdkClientException, IOException {
		String todayDateInStr = new SimpleDateFormat("MMddyyyy").format(new Date());
		String nowInStr = new SimpleDateFormat("HHmmss").format(new Date());
		String fileProcessingTime = todayDateInStr+nowInStr;
		String s3bucketname="dph-statelab-edss-providers";
		String region="us-east-1";
		String accesskey="";
		String secretkey="";
		String archivefoldername="covidLabResultFiles";
		String orgName = "East Side Clinical Lab (Sonicare) + Sunrise";
		String fileName = "SonicHealthcare_CoV_2_Data_2021_01_28_0501.csv";
		int fileExtPos = fileName.lastIndexOf(".");
		String fileNameWoExt = "", fileExt= "";
		if (fileExtPos >= 0 ) {
		    fileNameWoExt= fileName.substring(0, fileExtPos);
		    fileExt = fileName.substring(fileExtPos, fileName.length());
		} else {
		    fileNameWoExt= fileName;
		}

		String destinationPath = archivefoldername + "/" +orgName +"_" + fileNameWoExt + "_" + fileProcessingTime + fileExt;
		System.out.println(destinationPath);
		BasicAWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
		                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.fromName(region))
		                .build();
		s3client.putObject(s3bucketname, destinationPath, (InputStream) new ByteArrayInputStream(Files.readAllBytes(Paths.get("C:\\Users\\brajasekar\\Downloads\\SonicHealthcare_CoV_2_Data_2021_01_28_0501.csv"))), null);

	}

}
