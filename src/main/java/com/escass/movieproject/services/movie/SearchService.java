package com.escass.movieproject.services.movie;

import com.escass.movieproject.DTO.CharacterDTO;
import com.escass.movieproject.DTO.Movie_ImageDTO;
import com.escass.movieproject.mappers.movie.MovieMapper;
import com.escass.movieproject.vos.PageVo;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    private final MovieMapper movieMapper;

    public SearchService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public Pair<PageVo, List<Movie_ImageDTO>> searchMoviesByKeyword (String keyword, int requestPage) {

        int totalCount = movieMapper.getMovieCountByKeyword(keyword);

        PageVo pageVo = new PageVo(requestPage, totalCount);

        List<Movie_ImageDTO> movies = movieMapper.findMovieByKeyword(keyword, pageVo.offsetCount, pageVo.countPerPage);

        return Pair.of(pageVo, movies);
    }

    public List<CharacterDTO> searchPeopleByKeyword (String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return null;
        }
        return this.movieMapper.findCharacterByKeyword(keyword);
    }

    public List<Movie_ImageDTO> searchMoviesByPersonKeyword(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return null;
        }
        return this.movieMapper.searchMoviesByPersonKeyword(keyword);
    }

    public List<Map<String, Object>> searchMoviesByActor(String actorName) {
        return this.movieMapper.findMoviesByActorName(actorName);
    }

}
