package com.atse.group6.team10.controller.service;

import com.atse.group6.team10.model.Student;
import com.atse.group6.team10.model.Tutor;
import com.atse.group6.team10.model.User;
import com.googlecode.objectify.LoadResult;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserService {

    // ******* Singleton *******
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    // ******* Singleton End*******


    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    private UserService() {
    }

    public User getUserForId(Long userId) {
        LoadResult<User> loadResult = ofy().load().type(User.class).id(userId);
        return loadResult.now();
    }

    public User getUserForEMail(String email) {
        if(email == null)
            return null;
        LoadResult<User> loadResult = ofy().load().type(User.class).filter("email ==", email).first();
        return loadResult.now();
    }

    public Student createStudent(String email, String password) {
        byte[] salt = getNextSalt();
        byte[] hash = hash(password.toCharArray(), salt);
        Student s = new Student(email, hash, salt);
        ofy().save().entity(s).now();
        return s;
    }

    public Tutor createTutor(String email, String password) {
        byte[] salt = getNextSalt();
        byte[] hash = hash(password.toCharArray(), salt);
        Tutor t = new Tutor(email, hash, salt);
        ofy().save().entity(t).now();
        return t;
    }

    public boolean isExpectedPassword(User user, char[] password) {
        byte[] pwdHash = hash(password, user.getSalt());
        byte[] expectedHash = user.getPasswordHash();
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }

    private byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    private byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
}
