package com.example.DartsInventory.service;

import com.example.DartsInventory.dto.DartDto;
import com.example.DartsInventory.dto.DartPageResponse;
import com.example.DartsInventory.entities.Dart;
import com.example.DartsInventory.exceptions.DartNotFoundException;
import com.example.DartsInventory.exceptions.FileExistsException;
import com.example.DartsInventory.repositories.DartsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DartServiceImpl implements DartService{

    private final DartsRepository dartsRepository;

    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    public DartServiceImpl(DartsRepository dartsRepository, FileService fileService) {
        this.dartsRepository = dartsRepository;
        this.fileService = fileService;
    }

    @Override
    public DartDto addDart(DartDto dartDto, MultipartFile file) throws IOException {
        // upload file
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))) {
            throw new FileExistsException("File already exists! Enter another file name!");
        }
        String uploadedFileName = fileService.uploadFile(path, file);
        // set the value of field image as filename
        dartDto.setImage(uploadedFileName);
        // map dto to darts object
        Dart dart = new Dart(
                null,
                dartDto.getDart(),
                dartDto.getMaterial(),
                dartDto.getMaterialPercantage(),
                dartDto.getWeights(),
                dartDto.getBrand(),
                dartDto.getImage()
        );
        // save dart object
        Dart savedDart = dartsRepository.save(dart);
        // generate dart Url
        String imageUrl = baseUrl + "/file/" + uploadedFileName;
        // map dart object to dto object
        DartDto response = new DartDto(
                savedDart.getDartsId(),
                savedDart.getDart(),
                savedDart.getMaterial(),
                savedDart.getMaterialPercantage(),
                savedDart.getWeights(),
                savedDart.getBrand(),
                savedDart.getImage(),
                imageUrl
        );
        return response;
    }

    @Override
    public DartDto getDart(Integer dartsId) {

        // check data in DB. If exists, fetch data of given Id
        Dart dart = dartsRepository.findById(dartsId).orElseThrow(() -> new DartNotFoundException("Dart not found with ID!" + dartsId));

        // generate imageUrl
        String imageUrl = baseUrl + "/file/" + dart.getImage();

        // map to DartDto object and return it
        DartDto response = new DartDto(
                dart.getDartsId(),
                dart.getDart(),
                dart.getMaterial(),
                dart.getMaterialPercantage(),
                dart.getWeights(),
                dart.getBrand(),
                dart.getImage(),
                imageUrl
        );

        return response;
    }

    @Override
    public List<DartDto> getAllDarts() {
        // fetch all data from db
        List<Dart> darts = dartsRepository.findAll();

        List<DartDto> dartDtos = new ArrayList<>();

        // iterate through list, generate imageUrl for each dart object + map to dartdto object
        for(Dart dart : darts) {
            String imageUrl = baseUrl + "/file/" + dart.getImage();
            DartDto dartDto = new DartDto(
                    dart.getDartsId(),
                    dart.getDart(),
                    dart.getMaterial(),
                    dart.getMaterialPercantage(),
                    dart.getWeights(),
                    dart.getBrand(),
                    dart.getImage(),
                    imageUrl
            );
            dartDtos.add(dartDto);
        }
        return dartDtos;
    }

    @Override
    public DartDto updateDart(Integer dartsId, DartDto dartDto, MultipartFile file) throws IOException {
        // check if dart object exist with given dartsId
        Dart dt = dartsRepository.findById(dartsId).orElseThrow(() -> new DartNotFoundException("Dart not found with ID!" + dartsId));

        // if file is null -> do nothing, else -> then delete existing file and upload new file
        String fileName = dt.getImage();
        if(file != null) {
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
        }

        // set dartDtos image value
        dartDto.setImage(fileName);

        // map it to Dart object
        Dart dart = new Dart(
                dt.getDartsId(),
                dartDto.getDart(),
                dartDto.getMaterial(),
                dartDto.getMaterialPercantage(),
                dartDto.getWeights(),
                dartDto.getBrand(),
                dartDto.getImage()
        );
        // save dart object and return saved dart object
        Dart updatedDart = dartsRepository.save(dart);

        // generate imageurl
        String imageUrl = baseUrl + "/file/" + fileName;

        // map to dartdto and return it
        DartDto response = new DartDto(
                dart.getDartsId(),
                dart.getDart(),
                dart.getMaterial(),
                dart.getMaterialPercantage(),
                dart.getWeights(),
                dart.getBrand(),
                dart.getImage(),
                imageUrl
        );
        return response;
    }

    @Override
    public String deleteDart(Integer dartsId) throws IOException {

        // check if dart object exists
        Dart dt = dartsRepository.findById(dartsId).orElseThrow(() -> new DartNotFoundException("Dart not found with ID!" + dartsId));

        // delete file
        Files.deleteIfExists(Paths.get(path + File.separator + dt.getImage()));
        // delete movie object
        dartsRepository.delete(dt);

        return "Dart deleted with id = " + dt.getDartsId();
    }

    @Override
    public DartPageResponse getAllDartsWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Dart> dartPages = dartsRepository.findAll(pageable);
        List<Dart> darts = dartPages.getContent();

        List<DartDto> dartDtos = new ArrayList<>();

        // iterate through list, generate imageUrl for each dart object + map to dartdto object
        for(Dart dart : darts) {
            String imageUrl = baseUrl + "/file/" + dart.getImage();
            DartDto dartDto = new DartDto(
                    dart.getDartsId(),
                    dart.getDart(),
                    dart.getMaterial(),
                    dart.getMaterialPercantage(),
                    dart.getWeights(),
                    dart.getBrand(),
                    dart.getImage(),
                    imageUrl
            );
            dartDtos.add(dartDto);
        }

        return new DartPageResponse(dartDtos, pageNumber, pageSize,
                                    dartPages.getTotalElements(),
                                    dartPages.getTotalPages(),
                                    dartPages.isLast());
    }

    @Override
    public DartPageResponse getAllDartsWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir) {

        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Dart> dartPages = dartsRepository.findAll(pageable);
        List<Dart> darts = dartPages.getContent();

        List<DartDto> dartDtos = new ArrayList<>();

        // iterate through list, generate imageUrl for each dart object + map to dartdto object
        for(Dart dart : darts) {
            String imageUrl = baseUrl + "/file/" + dart.getImage();
            DartDto dartDto = new DartDto(
                    dart.getDartsId(),
                    dart.getDart(),
                    dart.getMaterial(),
                    dart.getMaterialPercantage(),
                    dart.getWeights(),
                    dart.getBrand(),
                    dart.getImage(),
                    imageUrl
            );
            dartDtos.add(dartDto);
        }

        return new DartPageResponse(dartDtos, pageNumber, pageSize,
                dartPages.getTotalElements(),
                dartPages.getTotalPages(),
                dartPages.isLast());

    }
}
