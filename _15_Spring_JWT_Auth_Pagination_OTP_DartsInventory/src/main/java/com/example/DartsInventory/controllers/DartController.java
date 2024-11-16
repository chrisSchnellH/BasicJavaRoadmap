package com.example.DartsInventory.controllers;

import com.example.DartsInventory.dto.DartDto;
import com.example.DartsInventory.dto.DartPageResponse;
import com.example.DartsInventory.exceptions.EmptyFileException;
import com.example.DartsInventory.service.DartService;
import com.example.DartsInventory.utils.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dart")
public class DartController {

    private final DartService dartService;

    public DartController(DartService dartService) {
        this.dartService = dartService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add-dart")
    public ResponseEntity<DartDto> addDartHandler(@RequestPart MultipartFile file,
                                                  @RequestPart String dartDto) throws IOException, EmptyFileException {
        if (file.isEmpty()) {
            throw new EmptyFileException("File is empty! Send another file!");
        }
        DartDto dto = convertToDartDto(dartDto);
        return new ResponseEntity<>(dartService.addDart(dto, file), HttpStatus.CREATED);
    }

    @GetMapping("/{dartsId}")
    public ResponseEntity<DartDto> getDartHandler(@PathVariable Integer dartsId) {
        return ResponseEntity.ok(dartService.getDart(dartsId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DartDto>> gettAllDartsHanlder() {
        return ResponseEntity.ok(dartService.getAllDarts());
    }

    @PutMapping("/update/{dartsId}")
    public ResponseEntity<DartDto> updateDartHandler(@PathVariable Integer dartsId,
                                                     @RequestPart MultipartFile file,
                                                     @RequestPart String dartDtoObj) throws IOException {
        if (file.isEmpty()) file = null;
        DartDto dartDto = convertToDartDto(dartDtoObj);
        return ResponseEntity.ok(dartService.updateDart(dartsId, dartDto, file));
    }

    @DeleteMapping("/delete/{dartsId}")
    public ResponseEntity<String> deleteDartHandler(@PathVariable Integer dartsId) throws IOException {
        return ResponseEntity.ok(dartService.deleteDart(dartsId));
    }

    @GetMapping("/allDartsPage")
    public ResponseEntity<DartPageResponse> getDartsWithPagination(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
    ) {
        return ResponseEntity.ok(dartService.getAllDartsWithPagination(pageNumber, pageSize));
    }

    @GetMapping("/allDartsPageSort")
    public ResponseEntity<DartPageResponse> getDartsWithPaginationAndSorting(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String dir
    ) {
        return ResponseEntity.ok(dartService.getAllDartsWithPaginationAndSorting(pageNumber, pageSize, sortBy, dir));
    }

    private DartDto convertToDartDto(String dartDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(dartDtoObj, DartDto.class);
    }
}
