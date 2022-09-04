package com.monitor.db;

import com.monitor.domain.Attendee;
import com.monitor.util.AppStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<AppStats,Integer> {
}
