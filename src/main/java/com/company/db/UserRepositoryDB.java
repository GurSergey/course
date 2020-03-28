package com.company.db;

import com.company.enitities.VoterEntity;
import com.company.exceptions.SelectException;
import com.company.repositories.UserRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryDB  implements UserRepository {

    public static String hashedPassword(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    public VoterEntity[] getAllUsers() throws SelectException{
        ArrayList<VoterEntity> voters = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, login, registration_date,  name, password, phone" +
                            " FROM voter WHERE login = ? and password = ?");
            while (resultSet.next()) {
                voters.add(new VoterEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return voters.toArray(new VoterEntity[voters.size()]);
    }


    public VoterEntity getUserByLoginPassword(String login, String password) throws SelectException {
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, login, registration_date,  name, password, phone" +
                            " FROM voter WHERE login = ? and password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashedPassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next() ) {
                return new VoterEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
        } catch (SQLException e){
            throw new SelectException();
        }
        return null;
    }
}
