package com.example.DartsInventory.service;

import com.example.DartsInventory.dto.DartDto;
import com.example.DartsInventory.dto.DartPageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DartService {

    DartDto addDart(DartDto dartDto, MultipartFile file) throws IOException;

    DartDto getDart(Integer dartsId);

    List<DartDto> getAllDarts();

    DartDto updateDart(Integer dartsId, DartDto dartDto, MultipartFile file) throws IOException;

    String deleteDart(Integer dartsId) throws IOException;

    DartPageResponse getAllDartsWithPagination(Integer pageNumber, Integer pageSize);

    DartPageResponse getAllDartsWithPaginationAndSorting(Integer pageNumber, Integer pageSize,
                                                         String sortBy, String dir);
}
