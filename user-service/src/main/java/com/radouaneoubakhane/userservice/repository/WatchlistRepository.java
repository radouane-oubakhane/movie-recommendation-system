package com.radouaneoubakhane.userservice.repository;

import com.radouaneoubakhane.userservice.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
}
