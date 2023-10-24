package com.example.demo.Repositories;

import com.example.demo.Constants.ProductType;
import com.example.demo.Models.Investment;
import com.example.demo.Utils.DbUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InvestmentRepository {
    private Connection conn;

    public Investment saveInvestment(int investorId, Investment investment) {
        try (Connection conn = DbUtil.createConnectionViaUserPwd();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO investments(investor_id, product_type, current_balance) VALUES (?,?,?)")) {
            preparedStatement.setInt(1, investorId); // Use the provided investorId
            preparedStatement.setString(2, String.valueOf(investment.getProductType()));
            preparedStatement.setInt(3, investment.getCurrentBalance());

            preparedStatement.executeUpdate();
            System.out.println(investment);
            return investment;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Investment saveInvestment2(int investorId, Investment investment) {
        try {
            this.conn = DbUtil.createConnectionViaUserPwd();

            String query = "INSERT INTO investments(investor_id, product_type_id, current_balance) VALUES (?,?,?)";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, investorId);
            preparedStatement.setString(2, String.valueOf(investment.getProductType())); // Storing the enum as an integer
            preparedStatement.setInt(3, investment.getCurrentBalance());
            preparedStatement.executeUpdate();
            System.out.println(investment);
            return investment;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (this.conn != null) {
                try {
                    this.conn.close();
                } catch (SQLException e) {
                    // Handle the closing exception if necessary
                }
            }
        }
    }

    public List<Investment> getAllInvestmentsByInvestorId(int investorId) {
        List<Investment> investmentsList = new ArrayList<>();
        try {
            this.conn = DbUtil.createConnectionViaUserPwd();
            String query = "SELECT * FROM investments WHERE investor_id = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, investorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Investment investments = new Investment();
                investments.setInvestorId(resultSet.getInt("investor_id"));
                investments.setProductType(ProductType.valueOf(resultSet.getString("product_type")));
                investments.setCurrentBalance(resultSet.getInt("current_balance"));
                System.out.println("Repository:  " + investments);
                investmentsList.add(investments);
            }
            return investmentsList;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}