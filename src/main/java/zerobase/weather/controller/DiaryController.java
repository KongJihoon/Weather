package zerobase.weather.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }


    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "날짜에 맞는 날씨 일기 작성")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
        diaryService.createDiary(date,text);

    }
    @ApiOperation("선택한 날짜의 모든 일기 데이터 조회")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "조회할 날짜", example = "2024-01-01") LocalDate date){
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간 중에 모든 일기 데이터 조회")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@ApiParam(value = "조회할 기간이 첫번째 날", example = "2024-01-01") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간이 마지막 날", example = "2024-01-01") LocalDate endDate){
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
    @ApiOperation("특정 날짜 첫번째 일기 수정")
    void updateDiary(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){

        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("/delete/diary")
    @ApiOperation("특정 날짜의 일기 삭제")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "삭제할 날짜", example = "2024-01-01") LocalDate date){

        diaryService.deleteDiary(date);
    }

}