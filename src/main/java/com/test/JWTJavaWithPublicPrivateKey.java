package com.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTJavaWithPublicPrivateKey {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidKeyException, SignatureException {
    	 KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
         generator.initialize(2048, new SecureRandom());
         KeyPair keyPair = generator.generateKeyPair();
         Base64.Encoder encoder = Base64.getEncoder();
         PrivateKey pk = keyPair.getPrivate();
         PublicKey pubKey = keyPair.getPublic();
         System.out.println("Private: " + pk.getFormat());
         System.out.println(encoder.encodeToString(pk.getEncoded()));
         System.out.println("Public: " + pubKey.getFormat());
         System.out.println(encoder.encodeToString(pubKey.getEncoded()));
         String message = String.format("{\"systemId\" : \"%s\", \"userId\": \"%s\"}", "MESSAGECONFIG", "super");
         Signature privateSignature;
         privateSignature = Signature.getInstance("SHA256withRSA");
         privateSignature.initSign(pk);
         privateSignature.update(message.getBytes());
         byte[] signature = privateSignature.sign();
         System.out.println(Base64.getEncoder().encodeToString(signature));


    }
   
}