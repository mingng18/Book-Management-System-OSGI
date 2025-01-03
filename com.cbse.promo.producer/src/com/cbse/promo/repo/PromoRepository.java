package com.cbse.promo.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cbse.promo.model.PromoCode;
import com.cbse.promo.model.PromoCriteria;

public class PromoRepository {

	public void createPromoCode(PromoCode promoCode) {
		String query = "INSERT INTO promos (promo_code, name, discount_rate, effective_start_timestamp, effective_end_timestamp, is_deleted) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, promoCode.getPromoCode());
			statement.setString(2, promoCode.getName());
			statement.setDouble(3, promoCode.getDiscountRate());
			statement.setTimestamp(4, new Timestamp(promoCode.getEffectiveStartTimestamp().getTime()));
			statement.setTimestamp(5, new Timestamp(promoCode.getEffectiveEndTimestamp().getTime()));
			statement.setBoolean(6, false);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating promo code.");
		}
	}

	public void updatePromoCode(int id, PromoCode updatedPromoCode) {
		String query = "UPDATE promos SET promo_code = ?, name = ?, discount_rate = ?, effective_start_timestamp = ?, effective_end_timestamp = ?, is_deleted = ? WHERE id = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, updatedPromoCode.getPromoCode());
			statement.setString(2, updatedPromoCode.getName());
			statement.setDouble(3, updatedPromoCode.getDiscountRate());
			statement.setTimestamp(4, new Timestamp(updatedPromoCode.getEffectiveStartTimestamp().getTime()));
			statement.setTimestamp(5, new Timestamp(updatedPromoCode.getEffectiveEndTimestamp().getTime()));
			statement.setBoolean(6, updatedPromoCode.isDeleted());
			statement.setInt(7, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error updating promo code.");
		}
	}

	public void deletePromoCode(int id) {
		String query = "UPDATE promos SET is_deleted = TRUE WHERE id = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting promo code.");
		}
	}

	public void addPromoCriteriaByPromoId(PromoCriteria promoCriteria, int promoId) {
		String query = "INSERT INTO promo_criterias (promo_id, description, condition_type, condition_value) VALUES (?, ?, ?, ?)";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, promoId);
			statement.setString(2, promoCriteria.getDescription());
			statement.setString(3, promoCriteria.getConditionType());
			statement.setString(4, promoCriteria.getConditionValue());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error adding promo criteria.");
		}
	}

	public void updatePromoCriteriaByPromoId(int promoId, PromoCriteria updatedPromoCriteria) {
		String query = "UPDATE promo_criterias SET description = ?, condition_type = ?, condition_value = ? WHERE promo_id = ? AND id = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, updatedPromoCriteria.getDescription());
			statement.setString(2, updatedPromoCriteria.getConditionType());
			statement.setString(3, updatedPromoCriteria.getConditionValue());
			statement.setInt(4, promoId);
			statement.setInt(5, updatedPromoCriteria.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error updating promo criteria.");
		}
	}

	public void deletePromoCriteriaByPromoId(int promoId, int criteriaId) {
		String query = "DELETE FROM promo_criterias WHERE promo_id = ? AND id = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, promoId);
			statement.setInt(2, criteriaId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting promo criteria.");
		}
	}

	public PromoCode getPromoCode(String code) {
		String query = "SELECT * FROM promos WHERE promo_code = ? AND is_deleted = FALSE";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, code);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				PromoCode promoCode = new PromoCode(resultSet.getInt("id"), resultSet.getString("promo_code"),
						resultSet.getString("name"), resultSet.getDouble("discount_rate"),
						resultSet.getTimestamp("effective_start_timestamp"),
						resultSet.getTimestamp("effective_end_timestamp"), resultSet.getBoolean("is_deleted"), null

				);
				return promoCode;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting promo code.");
		}
		return null;
	}

	public List<PromoCode> getAllActivePromoCodes() {
		List<PromoCode> promoCodes = new ArrayList<>();
		String query = "SELECT * FROM promos WHERE is_deleted = FALSE";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				PromoCode promoCode = new PromoCode(resultSet.getInt("id"), resultSet.getString("promo_code"),
						resultSet.getString("name"), resultSet.getDouble("discount_rate"),
						resultSet.getTimestamp("effective_start_timestamp"),
						resultSet.getTimestamp("effective_end_timestamp"), resultSet.getBoolean("is_deleted"), null

				);
				promoCodes.add(promoCode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting all active promo codes.");
		}
		return promoCodes;
	}

	public List<PromoCriteria> getAllPromoCriteriaByPromoId(int promoId) {
		List<PromoCriteria> promoCriteriaList = new ArrayList<>();
		String query = "SELECT * FROM promo_criterias WHERE promo_id = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, promoId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				PromoCriteria promoCriteria = new PromoCriteria(resultSet.getInt("id"),
						resultSet.getString("description"), resultSet.getString("condition_type"),
						resultSet.getString("condition_value"));
				promoCriteriaList.add(promoCriteria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error getting promo criteria by promo ID.");
		}
		return promoCriteriaList;
	}

}
