package com.example.demo.Repositories;

import com.example.demo.Models.Investor;
import com.example.demo.Utils.DbUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InvestorRepository {
    private Connection conn;

    public int saveInvestor(Investor investors) throws Exception {
        System.out.println(investors);

        try (Connection conn = DbUtil.createConnectionViaUserPwd();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO investors(name, surname, dob, address, mobile_number, email) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, investors.getName());
            preparedStatement.setString(2, investors.getSurname());
            preparedStatement.setDate(3, new java.sql.Date(investors.getDob().getTime()));
            preparedStatement.setString(4, investors.getAddress());
            preparedStatement.setInt(5, investors.getMobileNumber());
            preparedStatement.setString(6, investors.getEmail());
            System.out.println(investors);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insertion failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated investor_id
                } else {
                    throw new SQLException("Insertion failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Investor> getAllInvestors() throws Exception {
        List<Investor> investorList = new ArrayList<>();

        try {
            this.conn = DbUtil.createConnectionViaUserPwd();
            String query = "SELECT * FROM investors";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Investor investor = new Investor();
                investor.setName(resultSet.getString("name"));
                investor.setSurname(resultSet.getString("surname"));
                investor.setDob(resultSet.getDate("dob"));
                investor.setAddress(resultSet.getString("address"));
                investor.setMobileNumber(resultSet.getInt("mobile_number"));
                investor.setEmail(resultSet.getString("email"));
                investorList.add(investor);
            }

        } catch (SQLException e) {
            if (this.conn != null) {
                this.conn.close();
            }
            throw new SQLException(e);
        }

        return investorList;
    }

    public Investor getInvestorById(int id) {
        System.out.println("Repo     " + id);
        Investor investor = new Investor();
        try {
            this.conn = DbUtil.createConnectionViaUserPwd();
            String query = "SELECT * FROM investors WHERE id=? LIMIT 1";

            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                investor.setName(resultSet.getString("name"));
                investor.setSurname(resultSet.getString("surname"));
                investor.setDob(resultSet.getDate("dob"));
                investor.setAddress(resultSet.getString("address"));
                investor.setMobileNumber(resultSet.getInt("mobile_number"));
                investor.setEmail(resultSet.getString("email"));
                System.out.println("Repository:  " + investor);
                return investor;
            }

            return null; // Return null if no matching record is found
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
