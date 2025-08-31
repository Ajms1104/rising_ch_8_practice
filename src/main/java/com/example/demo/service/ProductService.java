package com.example.demo.service;

import com.example.demo.controller.dto.Product.ProductCreateRequestDto;
import com.example.demo.controller.dto.Product.ProductCreatResponseDto;
import com.example.demo.controller.dto.User.UserProductResponseDto;
import com.example.demo.repository.base.BaseRepository;
import com.example.demo.repository.base.entity.Base;
import com.example.demo.repository.photo.PhotoRepository;
import com.example.demo.repository.photo.entity.Photo;
import com.example.demo.repository.photo.entity.PhotoStatus;
import com.example.demo.repository.userproduct.ProductRepository;
import com.example.demo.repository.userproduct.entity.Product;
import com.example.demo.repository.userproduct.entity.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor

public class ProductService { //상품 관련 총 서비스
    private final ProductRepository productRepository;
    private final PhotoRepository photoRepository;
    private final BaseRepository baseRepository;

    //이미지 저장
    private final String UPLOAD_DIR = "uploads/";

    // 그 중 특정 유저 상품 조회 서비스 | API 4번
    @Transactional
    public List<UserProductResponseDto> findProductsByUser() {
        // Spring Security 컨텍스트 | 인증된 사용자 정보 가져오기.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "인증되지 않은 사용자".equals(
            authentication.getPrincipal())) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        //로그인이 되어 있다면
        User currentUser = (User) authentication.getPrincipal();

        // Repository를 통해 현재 사용자의 모든 상품 조회
        List<Product> userProducts = productRepository.findAllByUser(currentUser);

        // 조회된 상품(Entity) 리스트 ->Response DTO 리스트 변환 -> 반환
        return userProducts.stream()
            .map(UserProductResponseDto::from)
            .collect(Collectors.toList());
    }

    //그 중 상품 생성서비스 | API 3번
    @Transactional
    public ProductCreatResponseDto createProduct(ProductCreateRequestDto requestDto) {
        // 베이스 제품자체 존재 유무
        Base baseProduct = baseRepository.findByName(requestDto.getBasename())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 베이스 제품입니다."));

        // Spring Security 컨텍스트 | 인증된 사용자 정보 가져오기.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // GPT참고
        User currentUser = (User) authentication.getPrincipal();

        // 상품 생성 | Dto & Entity 매핑
        Product newProduct = Product.create(
            requestDto.getProductname(),
            requestDto.getComment(),
            currentUser,
            baseProduct
        );
        Product savedProduct = productRepository.save(newProduct);

        // 이미지 업로드 | Dto & Entity 매핑
        List<String> photoUrls = new ArrayList<>();
        for (MultipartFile file : requestDto.getPhoto()) {
            if (file.isEmpty()) continue;

            // 파일 저장 로직 | 여기부터
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            try {
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("파일 저장에 실패했습니다.", e);
            }

            String fileUrl = "/files/" + fileName; // 클라이언트가 접근할 URL
            photoUrls.add(fileUrl);
            // 여기까지 제미나이가 도와줌..

            // 사진 생성 | Dto & Entity 매핑
            Photo newPhoto = Photo.create(
                fileName,
                null,
                fileUrl,
                PhotoStatus.Registered // 초기 상태 '등록'
            );
            photoRepository.save(newPhoto);
        }

        // 최종 결과 반환
        return ProductCreatResponseDto.from(savedProduct,photoUrls );
    }
}

