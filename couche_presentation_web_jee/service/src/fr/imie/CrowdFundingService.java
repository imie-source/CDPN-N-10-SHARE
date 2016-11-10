package fr.imie;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;


@Dependent
@Default
@Regular
public class CrowdFundingService implements ICrowdFundingService {

	private @Inject ICrowdFundingDAO crowdFundingDAO;
	private @Inject IContributionDAO contributionDAO;
	
	@Override
	public List<CrowdFundingEntity> getAllCrowdFunfingDTO() {
		try {
			List<CrowdFundingEntity> out= crowdFundingDAO.getCrowdFundings();
			for (CrowdFundingEntity crowdFundingEntity : out) {
				crowdFundingEntity.setDons(contributionDAO.getContributionsByCrowdFunding(crowdFundingEntity));
				System.out.println(crowdFundingEntity.getDons());
			}
			
			return out;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public CrowdFundingEntity getByIdCrowdfundingDTO(Integer id) {
		try {
			CrowdFundingEntity crowdFundingById = crowdFundingDAO.getCrowdFundingById(id);
			crowdFundingById.setDons(contributionDAO.getContributionsByCrowdFunding(crowdFundingById));
			return crowdFundingById;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public CrowdFundingEntity saveCrowdfundingDTO(CrowdFundingEntity projet) {
		CrowdFundingEntity out= null;
		try {
			if(projet.getId()==null){
				out=crowdFundingDAO.addCrowdFundingDTO(projet);
			}else{
				out=crowdFundingDAO.updateCrowdFundingDTO(projet);			
			}
			return out;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

}
