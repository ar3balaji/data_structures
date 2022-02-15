package com.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;

public class SmartCard {
	static final SimpleDateFormat casetivityFormat = new SimpleDateFormat("dd/MM/yyyy");
	static final SimpleDateFormat fhirFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException, ParseException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, JOSEException, WriterException {
	/*	String JSON_SOURCE = "{\"@id\":\"1\",\"createdById\":\"00000000-0000-0000-0000-000000000901\",\"createdDate\":\"2021-09-10T16:10:40.222Z\",\"createdDateString\":\"2021-09-10 12:10:40\",\"lastModifiedById\":\"00000000-0000-0000-0000-000000000901\",\"lastModifiedDate\":\"2021-09-10T16:11:15.152Z\",\"lastModifiedDateString\":\"2021-09-10 12:11:15\",\"id\":\"9bb5aaa4-0060-1001-817b-d079ad300001\",\"entityVersion\":2,\"entityType\":\"Person\",\"casetivityCanEdit\":true,\"casetivityCanDelete\":true,\"casetivityCanMerge\":true,\"firstName\":\"Neal\",\"lastName\":\"Brenner\",\"email\":\"nbrenner@ssg-llc.com\",\"birthDate\":\"1981-02-03\",\"birthDateString\":\"02/03/1981\",\"miisIdentifier\":\"8659251\",\"time\":\"2021-09-10T16:10:40.304Z\",\"timeString\":\"2021-09-10 12:10:40\",\"key\":\"MWYPCSL2WO7BpRSfJPcgRj5TO\",\"ipAddress\":\"108.7.203.218\",\"password\":\"$2a$10$P0slW7Q4aPdfHemtnQ1hh.UDkXXYcybyQuqj4PUZ8M0/G7lxICIje\",\"failedAttempts\":1,\"hasPossibleMatches\":false,\"gender\":{\"@id\":\"2\",\"createdById\":\"8e1fa3ce-11c2-11ec-9b7b-02ff9710eab7\",\"createdDate\":\"2021-09-09T23:07:45.232Z\",\"createdDateString\":\"2021-09-09 19:07:45\",\"lastModifiedById\":\"8e1fa3ce-11c2-11ec-9b7b-02ff9710eab7\",\"lastModifiedDate\":\"2021-09-09T23:07:45.232Z\",\"lastModifiedDateString\":\"2021-09-09 19:07:45\",\"id\":\"a861083d-459b-1000-817b-ccd13be3073d\",\"entityVersion\":1,\"entityType\":\"Concept\",\"casetivityCanEdit\":true,\"casetivityCanDelete\":true,\"casetivityCanMerge\":true,\"code\":\"M\",\"display\":\"%{concepts.gender.male}\",\"description\":\"\",\"group\":\"\",\"active\":true,\"title\":\"Gender: %{concepts.gender.male}\",\"subtitle\":\"\",\"hasPossibleMatches\":false,\"valueSetId\":\"a861083d-459b-1000-817b-ccd13be30739\",\"casetivityExtraFields\":{}},\"genderId\":\"a861083d-459b-1000-817b-ccd13be3073d\",\"genderCode\":\"M\",\"casetivityExtraFields\":{},\"immunizations\":{\"errors\":\"ERR\",\"matchFound\":true,\"qr\":\"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\",\"miisId\":\"8659251\",\"lastName\":\"BRENNER\",\"firstName\":\"NEAL\",\"middleName\":\"E\",\"fullName\":\"NEAL E BRENNER\",\"phones\":[\"6176370545\"],\"emails\":[\"nbrenner@ssg-llc.com\"],\"addresses\":[{\"line1\":\"sdfs\",\"city\":\"sdfsd\",\"state\":\"MA\",\"zip\":\"02492\"}],\"vaccines\":[{\"cvx\":\"210\",\"isCovid\":true,\"isFlu\":false,\"name\":\"COVID-19 AstraZeneca\",\"nameUpper\":\"COVID-19 ASTRAZENECA\",\"provider\":\"DPH-Bureau of Infectious Disease Prevention Response and Services Test\",\"lot\":\"sdfsdf\",\"valid\":true,\"dateSort\":\"20210901\",\"date\":\"09/01/2021\",\"manufacturer\":\"AstraZeneca\",\"dose\":\"0.5\"},{\"cvx\":\"168\",\"isCovid\":false,\"isFlu\":true,\"name\":\"Flu-aIIV3\",\"nameUpper\":\"FLU-AIIV3\",\"provider\":\"DPH-Bureau of Infectious Disease Prevention Response and Services Test\",\"lot\":\"54654\",\"valid\":true,\"dateSort\":\"20210907\",\"date\":\"09/07/2021\",\"manufacturer\":\"Novartis Vaccines (Chiron, Ciba-Geigy, Sandoz, PowderJect, Celltech Medeva, Evans)\",\"dose\":\"0.5\"}],\"hadCovidVaccine\":true}}";
		Map<String,Object> personData =
		        new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);
		Map<String,Object> smartHealthCard = new HashMap<String, Object>();
		smartHealthCard.put("iss","https://spec.smarthealth.cards/examples/issuer");
		smartHealthCard.put("nbf", Instant.now().toEpochMilli());
		
		
		Map<String,Object> credSubject = new HashMap<String, Object>();
		credSubject.put("fhirVersion", "4.0.1");

		Map<String,Object> fhirBundle = new HashMap<String, Object>();
		fhirBundle.put("resourceType", "Bundle");
		fhirBundle.put("type", "collection");

		ArrayList<Object> entries = new ArrayList<Object>();
		entries.add(getPersonDataJson(personData));
		
		HashMap<String,Object> immunizations = (HashMap<String, Object>)(personData.get("immunizations"));
		ArrayList<Object> vaccines = (ArrayList<Object>)(immunizations.get("vaccines"));
		for(int i = 0; i < (vaccines).size(); i++) {
			entries.add(getVaccineData((HashMap<String,Object>)vaccines.get(i), i+1));
		}

		fhirBundle.put("entry", entries.toArray());
		
		
		credSubject.put("fhirBundle", fhirBundle);

		Map<String,Object> vc = new HashMap<String, Object>();
		vc.put("type", Arrays.asList("https://smarthealth.cards#health-card", "https://smarthealth.cards#immunization", "https://smarthealth.cards#covid19"));
		vc.put("credentialSubject", credSubject);		

		smartHealthCard.put("vc", vc);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(smartHealthCard);
		byte[] result = json.getBytes("UTF-8");
		
		// Generate an EC key pair
		ECKey ecJWK = new ECKeyGenerator(Curve.P_256)
		    .keyID("123")
		    .generate();
		ECKey ecPublicJWK = ecJWK.toPublicJWK();

		// Create the EC signer
		JWSSigner signer = new ECDSASigner(ecJWK);
		// Creates the JWS object with payload
		JWSObject jwsObject = new JWSObject(
		    new JWSHeader.Builder(JWSAlgorithm.ES256).keyID(ecJWK.getKeyID()).build(),
		    new Payload(json));

		// Compute the EC signature
		jwsObject.sign(signer);

		// Serialize the JWS to compact form
		String s = jwsObject.serialize();

		// The recipient creates a verifier with the public EC key
		JWSVerifier verifier = new ECDSAVerifier(ecPublicJWK);

		 // Verify the EC signature
		System.out.println(jwsObject.verify(verifier));
		System.out.println(s);
		//System.out.println(jwsObject.getPayload().toString());
		
		
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(s, BarcodeFormat.QR_CODE, 400, 400);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    String encodedfile = new String(Base64.encodeBase64(pngData), "UTF-8");
	    System.out.println(encodedfile);
	    
	    JWSObject jwsObject1;

	    try {
	        jwsObject1 = JWSObject.parse(s);
	        System.out.println(jwsObject1.getPayload().toString());
	    } catch (java.text.ParseException e) {
	        // Invalid JWS object encoding
	    }
*/
		
	}

	private static Object getVaccineData(HashMap<String,Object> vaccineData, int vaccineIndex) throws ParseException {
		Map<String,Object> vaccine = new HashMap<String, Object>();
		vaccine.put("fullUrl", "resource:"+vaccineIndex);
		
		Map<String,Object> resource = new HashMap<String, Object>();
		
		resource.put("resourceType", "Immunization");
		resource.put("status", "completed");
		
		Map<String,Object> vaccineCode = new HashMap<String, Object>();
		HashMap<String,Object> codingValue = new HashMap<String, Object>();
		codingValue.put("system", "http://hl7.org/fhir/sid/cvx");
		codingValue.put("code", vaccineData.get("cvx"));

		vaccineCode.put("coding", Arrays.asList(codingValue));
		resource.put("vaccineCode", vaccineCode);

		HashMap<String, Object> resourcePatient = new HashMap<String, Object>();
		resourcePatient.put("reference", "resource:0");
		resource.put("patient", resourcePatient);
		resource.put("occurenceDateTime", fhirFormat.format(casetivityFormat.parse((String) vaccineData.get("date"))));
		
		HashMap<String, Object> performer = new HashMap<String, Object>();
		HashMap<String, Object> actor = new HashMap<String, Object>();
		actor.put("display", vaccineData.get("provider"));
		performer.put("actor", actor);
		
		resource.put("performer", Arrays.asList(performer));
		
		resource.put("lotNumber",vaccineData.get("log"));
		vaccine.put("resource", resource);
		return vaccine;
	}

	private static Object getPersonDataJson(Map<String, Object> personData) throws ParseException {
		Map<String,Object> personResource = new HashMap<String, Object>();
		personResource.put("fullUrl","resource:0");
		
		Map<String,Object> personResourceValue = new HashMap<String, Object>();
		personResourceValue.put("resourceType", "Patient");
		
		Map<String,Object> nameValue = new HashMap<String, Object>();
		nameValue.put("family", "Anyperson");
		nameValue.put("given", Arrays.asList(personData.get("firstName"), personData.get("lastName")));
		
		personResourceValue.put("name", Arrays.asList(nameValue));
		personResourceValue.put("birthDate",personData.get("birthDate"));
		
		personResource.put("resource",personResourceValue);
		return personResource;
	}
}
