package uon.spring_blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uon.spring_blog.domain.RefreshToken;
import uon.spring_blog.repository.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token")
                );
    }
}
