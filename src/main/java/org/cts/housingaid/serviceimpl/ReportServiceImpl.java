package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.AllocationRepository;
import org.cts.housingaid.dao.HousingApplicationRepository;
import org.cts.housingaid.dao.HousingProjectRepository;
import org.cts.housingaid.dao.ReportRepository;
import org.cts.housingaid.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    private final HousingApplicationRepository applicationRepository;

    private final HousingProjectRepository projectRepository;

    private final AllocationRepository allocationRepository;

    public Map<String, Object> getDashboardStats() {

        Map<String, Object> status = new HashMap<>();

        long totalApplications =
                applicationRepository.count();

        long totalAllocations =
                allocationRepository.count();

        long activeProjects =
                projectRepository.count();

        double allocationRate = 0.0;

        if (totalApplications > 0) {

            allocationRate =
                    ((double) totalAllocations / totalApplications) * 100;

        }

        status.put("totalApplications", totalApplications);

        status.put("activeProjects", activeProjects);

        status.put("allocationRate",
                Math.round(allocationRate * 10.0) / 10.0);

        status.put("avgLeadTime", "23 days");

        return status;
    }

}