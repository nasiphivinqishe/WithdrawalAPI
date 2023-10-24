package com.example.demo.Repositories;

import com.example.demo.Models.Withdrawal;
import com.example.demo.Utils.DbUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Repository
public class WithdrawalRepository {
    private Connection conn;

    public String getInvestmentType(int investmentId) throws Exception {
        conn = DbUtil.createConnectionViaUserPwd();
        try {
            String selectQuery = "SELECT product_type FROM investments WHERE id = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, investmentId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("product_type");
            } else {
                return null; // Handle the case where the investment doesn't exist
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int getInvestorAge(int investorId) throws Exception {
        conn = DbUtil.createConnectionViaUserPwd();
        try {
            String dobQuery = "SELECT dob FROM investors WHERE id = ?";
            PreparedStatement dobStatement = conn.prepareStatement(dobQuery);
            dobStatement.setInt(1, investorId);
            ResultSet dobResultSet = dobStatement.executeQuery();

            if (dobResultSet.next()) {
                Date dob = dobResultSet.getDate("dob");
                LocalDate dobLocalDate = dob.toLocalDate();
                LocalDate currentDate = LocalDate.now();
                int age = Period.between(dobLocalDate, currentDate).getYears();
                return age;
            } else {
                return 0; // Handle the case where the investor doesn't exist
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    private int calculateAge(Date dob) {
        LocalDate dobLocalDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dobLocalDate, currentDate);
        return period.getYears();
    }

    public double getCurrentBalance(int investmentId) throws Exception {
        conn = DbUtil.createConnectionViaUserPwd();
        try {
            String selectQuery = "SELECT current_balance FROM investments WHERE id = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, investmentId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("current_balance");
            } else {
                return 0.0; // Handle the case where the investment doesn't exist
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateCurrentBalance(int investmentId, double newBalance) throws Exception {
        conn = DbUtil.createConnectionViaUserPwd();
        try {
            String updateQuery = "UPDATE investments SET current_balance = ? WHERE id = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setDouble(1, newBalance);
            updateStatement.setInt(2, investmentId);
            updateStatement.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void createWithdrawals(Withdrawal withdrawal, double newBalance) throws Exception {
        System.out.println("Check why get error" + withdrawal);

        conn = DbUtil.createConnectionViaUserPwd();
        try {
            // Create the withdrawal and update the balance in the database
            String insertQuery = "INSERT INTO withdrawals(investment_id, withdrawal_amount, date_and_time) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setInt(1, withdrawal.getInvestmentId());
            preparedStatement.setDouble(2, withdrawal.getWithdrawalAmount());
            preparedStatement.setDate(3, new java.sql.Date(withdrawal.getDateAndTime().getTime()));
            preparedStatement.executeUpdate();

            String updateQuery = "UPDATE investments SET current_balance = ? WHERE id = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setDouble(1, newBalance);
            updateStatement.setInt(2, withdrawal.getInvestmentId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
