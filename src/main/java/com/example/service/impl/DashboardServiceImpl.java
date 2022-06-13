package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.entities.DatabaseChangeLog;
import com.example.exceptions.OverviewException;
import com.example.models.DatabaseChangeSummary;
import com.example.models.OverviewDetails;
import com.example.repositories.DatabaseChangeLogRepository;
import com.example.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	DatabaseChangeLogRepository changeLogRepository;
	
	@Value("${DASHBOARD_CHANGELOG_SUMMARY_LIMIT}")
	private Long maxChangeLogsForDashboard;
	
	@Value("${LIVE_RELEASE}")
	private String liveRelease;
	
	@Value("${NEXT_RELEASE}")
	private String nextRelease;
	
	@Value("${ENVIRONMENT_NAME}")
	private String environmentName;
	
	@Override
	public OverviewDetails getOverviewDetails() {

		OverviewDetails returnObject = OverviewDetails.builder().build();
		List<DatabaseChangeSummary> changeSummaryList = new ArrayList<>();
		List<DatabaseChangeLog> changeLogDetails = getChangeLogDetails();
		
		if (!Objects.isNull(changeLogDetails) && !changeLogDetails.isEmpty()) {
			
			Long executedCount = getTotalExecutions("EXECUTED");
			Long markRanCount = getTotalExecutions("MARK_RAN");
			
			changeLogDetails.stream().limit(this.maxChangeLogsForDashboard).forEach(change -> {
				
				changeSummaryList.add(DatabaseChangeSummary.builder()
						.id(change.getId())
						.author(change.getAuthor())
						.execType(change.getExectype())
						.build());
			});
			
			returnObject = OverviewDetails.builder()
					.executedCount(executedCount)
					.markRanCount(markRanCount)
					.recentContributor(changeLogDetails.get(0).getAuthor())
					.recentExecutor(changeLogDetails.get(0).getAuthor())
					.lastExecutedOn(changeLogDetails.get(0).getDateexecuted())
					.deploymentId(changeLogDetails.get(0).getDeploymentId())
					.changeSummary(changeSummaryList)
					.build();
		}
		
		returnObject.setEnvironment(this.environmentName);
		returnObject.setLiveRelease(this.liveRelease);
		returnObject.setNextRelease(this.nextRelease);
		return returnObject;
	}
	
	public List<DatabaseChangeLog> getChangeLogDetails() {
		return changeLogRepository.findAllChangeLogs();
	}
	
	public Long getTotalExecutions(String executeType) {
		return changeLogRepository.countOfExecutions(executeType);
	}

}
