package com.example.demo.controller.dto.Photo;

import com.example.demo.repository.photo.entity.PhotoHistory;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhotoHistoryDto {
        private LocalDate eventDate; // 이벤트 발생일
        private String oldStatus;    // 과거 상태
        private String nowStatus;    // 현재 상태

        public static PhotoHistoryDto from(PhotoHistory history) {
            return new PhotoHistoryDto(
                history.getUpdated_at(),      // 수정일자
                history.getOldstatus().name(),// Enum 타입
                history.getNowstatus().name() // Enum 타입
            );
        }

}
