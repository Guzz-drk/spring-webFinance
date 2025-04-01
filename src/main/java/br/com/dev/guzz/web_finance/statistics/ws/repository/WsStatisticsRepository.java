package br.com.dev.guzz.web_finance.statistics.ws.repository;

import br.com.dev.guzz.web_finance.statistics.ws.entity.WsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WsStatisticsRepository extends JpaRepository<WsStatistics, Long> {
}
