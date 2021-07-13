package code.resmed.rockpaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.resmed.rockpaper.model.GameStats;

public interface GameStatsRepository extends JpaRepository<GameStats, Long> {
}
