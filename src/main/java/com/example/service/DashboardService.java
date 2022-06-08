package com.example.service;

import org.springframework.stereotype.Component;
import com.example.models.OverviewDetails;

@Component
public interface DashboardService {

	public OverviewDetails getOverviewDetails();
}
