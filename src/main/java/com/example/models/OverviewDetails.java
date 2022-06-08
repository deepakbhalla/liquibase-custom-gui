package com.example.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OverviewDetails implements Serializable {

	private static final long serialVersionUID = -746681823708099612L;

	Long executedCount;
	Long markRanCount;
	String recentContributor;
	String recentExecutor;
	LocalDate lastExecutedOn;
	String deploymentId;
	String liveRelease;
	String nextRelease;
	String environment;
	List<DatabaseChangeSummary> changeSummary;
}
