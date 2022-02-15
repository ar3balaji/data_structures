package com.test;
import java.security.*;
import java.security.interfaces.*;
import java.util.*;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;

public class GenerateKeyPair {

	public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, JOSEException {
		// Generate EC key pair with P-256 curve
		KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
		gen.initialize(Curve.P_256.toECParameterSpec());
		KeyPair keyPair = gen.generateKeyPair();
		
		String keyId = UUID.randomUUID().toString();
		
		
		System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
		
		System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64String(keyPair.getPublic().getEncoded()));

		// Convert to JWK format
		JWK jwk = new ECKey.Builder(Curve.P_256, (ECPublicKey) keyPair.getPublic())
		    .privateKey((ECPrivateKey) keyPair.getPrivate())
		    .keyID(keyId)
		    .build();
		System.out.println(jwk);
	}

}
