package com.escass.movieproject.DTO;

import com.escass.movieproject.results.Result;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto<TResult extends Result, TPayload> {
    private TResult result;
    private TPayload payload;
}