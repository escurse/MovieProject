package com.escass.movieproject.services;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.mappers.TheaterMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterMapper theaterMapper;

    public RegionEntity[] findRegionAll() {
        return this.theaterMapper.getRegionAll();
    }

    public TheaterEntity[] getTheatersByRegion(String region) {
        if (region == null) {
            return null;
        }
        TheaterEntity[] theaters = this.theaterMapper.getTheatersByRegion(region);
        String[] addrs;
        for (TheaterEntity theater : theaters) {
            addrs = theater.getThAddr().split("\n");
            theater.setThAddr(Arrays.toString(addrs));
        }
        return theaters;
    }

    public Pair<Integer, Integer> getTheaterSeatCount(String theater) {
        return Pair.of(this.theaterMapper.getCinemaCountByTheater(theater),
                this.theaterMapper.getSeatCountByCinema(theater));
    }
}
