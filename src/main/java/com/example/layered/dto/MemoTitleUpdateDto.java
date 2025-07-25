package com.example.layered.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// title만 받는 전용 DTO 생성
@Getter
@NoArgsConstructor
public class MemoTitleUpdateDto {
    private String title;
}
