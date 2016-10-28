package fr.imie;

import java.sql.SQLException;
import java.util.List;

public interface ICrowdFundingDAO {
	public List<CrowdFundingEntity> getCrowdFundings() throws SQLException;

	CrowdFundingEntity addCrowdFundingDTO(CrowdFundingEntity crowdFundingDTO) throws SQLException ;

	CrowdFundingEntity getCrowdFundingById(Integer id) throws SQLException;

	CrowdFundingEntity updateCrowdFundingDTO(CrowdFundingEntity projet) throws SQLException;
}
